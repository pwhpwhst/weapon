local key=KEYS[1];

local result=redis.call("get",key);

return result;