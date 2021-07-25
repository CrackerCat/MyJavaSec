package main

import (
	"fmt"
	"net/http"
)

func main() {
	mux := http.NewServeMux()
	path := "D:\\Code\\DES\\Fastjson\\target\\classes"
	mux.Handle("/", http.StripPrefix("/", http.FileServer(http.Dir(path))))
	if err := http.ListenAndServe(":8000", mux); err != nil {
		fmt.Println("ok")
	}
}
