local jwt = require "resty.jwt"
local validators = require "resty.jwt-validators"

local M = {}

function finish_request_with_error(error)
    ngx.log(ngx.WARN, error)

    ngx.status = ngx.HTTP_UNAUTHORIZED
    ngx.header.content_type = "application/json; charset=utf-8"
    ngx.say("{\"error\": \"" .. error .. "\"}")
    ngx.exit(ngx.HTTP_UNAUTHORIZED)
end

function has_any_role(token_roles, roles)
    if token_roles == nil then
        return false
    end

    for _, role in pairs(roles) do
        for _, token_role in pairs(token_roles) do
            if token_role == role then return true end
        end
    end

    return false
end

function M.check(roles)
    -- try to find JWT token in Authorization header Bearer string
    local auth_header = ngx.var.http_Authorization
    local token = nil
    if auth_header then
        _, _, token = string.find(auth_header, "Bearer%s+(.+)")
    end

    -- if no JWT token, kick out an error and exit
    if not token then
        finish_request_with_error("No token")
    end

    -- validate any specific claims you need here
    -- https://github.com/SkyLothar/lua-resty-jwt#jwt-validators
    local claim_spec = {
        exp = validators.is_not_expired(),
        authorities = function (token_roles)
            local check_roles = not roles == nil
            if check_roles and not has_any_role(token_roles, roles) then
                error("Has no enough permissions")
            end
        end
    }

    -- make sure to set and put "env JWT_SECRET;" in nginx.conf
    local jwt_obj = jwt:verify(os.getenv("JWT_SECRET"), token, claim_spec)
    if not jwt_obj["verified"] then
        finish_request_with_error("Invalid token: ".. jwt_obj.reason)
    end

    ngx.req.set_header("X-UserName", jwt_obj.payload.user_name)
end

return M
