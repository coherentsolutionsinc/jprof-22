from jinja2 import Environment, FileSystemLoader
import re
import os
import yaml

NONE = 'none'

URL_PLACEHOLDER_REGEX = re.compile(r"\{.+?\}", re.IGNORECASE)
NGINX_CONFIG_TEMPLATE = 'nginx.conf.template'
NGINX_CONFIG = 'nginx.conf'
YML_SUBDOMAINS_CONFIGS_DIR = 'applications'

def api_location_regex(context_path, location, filename):
    url = location.get('url')
    if not url:
        raise Exception('{}: Url should be specified'.format(filename))

    return "^" + context_path + "(" + URL_PLACEHOLDER_REGEX.sub('\w+', url) + "(?:\?.*)?)$"

def parse_subdomains_config():
    all_upstreams = []
    all_subdomains = []
    for filename in os.listdir(YML_SUBDOMAINS_CONFIGS_DIR):
        if not filename.endswith(".yml"):
            continue

        with open(os.path.join(YML_SUBDOMAINS_CONFIGS_DIR, filename), 'r') as stream:
            subdomain_config = yaml.load(stream)

        subdomain_url = subdomain_config.get('subdomain-env')
        if not subdomain_url:
            raise Exception('{} should be specified in container'.format(filename))

        # subdomain static resources
        static_root = subdomain_config.get('static-root')

        # subdomain apis
        subdomain_locations = []
        subdomain = {'url': subdomain_url, 'static_root': static_root, 'locations': subdomain_locations}

        apis = subdomain_config.get('api')
        for api in apis:
            context_path = api.get('context-path', '/')

            # api upstream
            upstream_url = api.get('upstream-url')
            upstream_name = api.get('upstream-name')
            if not upstream_url:
                raise Exception('{} should be specified in container'.format(filename))

            upstream = {'name': upstream_name, 'url': upstream_url}

            if not any(upstream['name'] == upstream_name for upstream in all_upstreams):
                all_upstreams.append(upstream)

            # api locations
            locations = api.get('locations')
            if not locations:
                raise Exception('{}: Locations should be specified in api section'.format(filename))

            for location in locations:
                location['regex'] = api_location_regex(context_path, location, filename)
                location['upstream'] = upstream
                if location.get('security') == NONE:
                    location['nosecurity'] = True

                subdomain_locations.append(location)

        all_subdomains.append(subdomain)

    return (all_upstreams, all_subdomains)

if __name__ == "__main__":
    (all_upstreams, all_subdomains) = parse_subdomains_config()

    env = Environment(loader=FileSystemLoader('.'))
    template = env.get_template(NGINX_CONFIG_TEMPLATE)
    nginx_config_content = template.render(all_subdomains=all_subdomains, all_upstreams=all_upstreams)

    # save config
    with open(NGINX_CONFIG, "wb") as fh:
        fh.write(nginx_config_content)
