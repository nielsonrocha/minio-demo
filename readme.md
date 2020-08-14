# Projeto de demonstração de uso do MinIO com SpringBoot

## Container do MinIO
``docker run -p 9001:9000 -e "MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE" -e "MINIO_SECRET_KEY=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"  minio/minio server /data``

### Acessar via navegador
http://localhost:9001

Inserir o secret e criar o bucket demo