-- example HTTP POST script which demonstrates setting the
-- HTTP method, body, and adding a header

wrk.method = "POST"
wrk.body   = "[1,2,3,4,5]"
wrk.headers["Content-Type"] = "application/json"