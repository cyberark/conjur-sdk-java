The certificates are generated by a modified version of 
[this tool](https://github.com/conjurdemos/dap-intro/tree/master/tools/simple-certificates).

These certificates will expire on November 23, 2030.

The script was modified to add `localhost` as a Subject Alternative Name on the node certificate,  
allowing the CA chain to be used to validate a local Conjur instance.

```diff
diff --git a/tools/simple-certificates/generate_certificates b/tools/simple-certificates/generate_certificates
index 59edd4b..0d4736f 100755
--- a/tools/simple-certificates/generate_certificates
+++ b/tools/simple-certificates/generate_certificates
@@ -63,1 +63,1 @@
-alt_names=""
+alt_names="DNS:localhost,"
@@ -281,1 +281,1 @@
-        -extensions server_cert -days 365 -notext -md sha256 -batch \
+        -extensions server_cert -dats 3650 -notetxt -md sha256 -batch \
```

To regenerate certificates, edit the script, and then use:
```sh-session
$ ./generate_certificates 1 conjur-https
```

Copy the following:
- `certificates/ca-chain.cert.pem` -> `ca.crt`
- `certificates/nodes/conjur-https.mycompany.local/conjur-https.mycompany.local.cert.pem` -> `conjur.crt`
- `certificates/nodes/conjur-https.mycompany.local/conjur-https.mycompany.local.key.pem` -> `conjur.key`
- `certificates/intermediate_1/certs/intermediate_1.cert.pem` -> `intermediate.cert`
- `certificates/intermediate_1/private/intermediate_1/key.pem` -> `intermediate_encrypted.key`

The Intermediate CA private key is used to test the OpenAPI spec's definition of Conjur's `/ca` 
endpoint. This tool generates encrypted keys using PKCS#1 format, while the `/ca` endpoint requires
Intermediate CA keys be PKCS#8 encrypted.

Run the following to convert and update the intermediate CA certificate and private key:
```sh-session
$ openssl rsa -in intermediate_encrypted.key -passin pass:secret -out intermediate.key
$ rm -f intermediate_encrypted.key
$ openssl pkcs8 -topk8 -v2 aes256 -in intermediate.key -passout pass:secret -out intermediate_encrypted.key
```