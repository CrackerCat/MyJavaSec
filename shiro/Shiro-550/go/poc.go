package main

import (
	"bytes"
	"fmt"
	"io/ioutil"
	"net/http"
	"regexp"
	"strings"
)

func main() {
	target := "http://localhost:8080"
	loginUrl := "http://localhost:8080/doLogin"
	loginData := "username=admin&password=vulhub&rememberme=remember-me"

	headers := make(map[string]string)
	headers["User-Agent"] = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
		"AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36"
	headers["Cookie"] = "a=1; rememberMe=test"
	var trueCookie string
	_, resHeader, falsePage := request("GET", target, "", headers)
	if v, ok := resHeader["Set-Cookie"]; ok {
		if strings.Contains(v, "rememberMe=deleteMe;") {
			fmt.Println("[*] maybe shiro")
			headers["Cookie"] = "a=1; "
			headers["Content-Type"] = "application/x-www-form-urlencoded"
			_, resHeader, _ = request("POST", loginUrl, loginData, headers)
			if innerV, innerOk := resHeader["Set-Cookie"]; innerOk {
				re := regexp.MustCompile("rememberMe=(.*?);")
				result := re.FindAllStringSubmatch(innerV, -1)
				for _, res := range result {
					if res[1] != "deleteMe" {
						trueCookie = "a=1; rememberMe=" + res[1]
					}
				}
			}
		}
	}
	headers["Cookie"] = trueCookie
	_, resHeader, truePage := request("GET", target, "", headers)
	if v, ok := resHeader["Set-Cookie"]; ok {
		if !strings.Contains(v, "rememberMe=deleteMe;") {
			if string(truePage) != string(falsePage) {
				fmt.Println("[*] detected shiro!")
			}
		}
	}
}

// Request 发送http请求
func request(method string, url string, data string, headers map[string]string) (int, map[string]string, []byte) {
	client := http.Client{}
	var req *http.Request
	if len(data) != 0 {
		req, _ = http.NewRequest(method, url, bytes.NewBuffer([]byte(data)))
	} else {
		req, _ = http.NewRequest(method, url, nil)
	}
	for k, v := range headers {
		req.Header.Set(k, v)
	}
	resp, err := client.Do(req)
	if err == nil {
		var body []byte
		body, err = ioutil.ReadAll(resp.Body)
		defer func() {
			if resp != nil {
				_ = resp.Body.Close()
			}
		}()
		respHeader := make(map[string]string)
		for k, v := range resp.Header {
			respHeader[k] = strings.Join(v, "")
		}
		return resp.StatusCode, respHeader, body
	}
	return -1, nil, nil
}
