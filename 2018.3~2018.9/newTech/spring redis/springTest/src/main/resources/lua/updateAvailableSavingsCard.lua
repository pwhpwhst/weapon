local result ={}
result["@class"] = "com.juvenxu.mvnbook.helloworld.Result"
-- redis.call('SET', KEYS[1], ARGV[1])
-- redis.call('SET', KEYS[1], "abc")
redis.call('SET', KEYS[1], KEYS[3])
local encodestr = cjson.encode(result)
print(encodestr)
return  encodestr


