{
    "query": {
        "multi_match" : {
            "query":  "%s", 
            "type":   "cross_fields",
            "fields": [ "name", "brand^3", "outlet.mall^5" ] 
        }
    }
}