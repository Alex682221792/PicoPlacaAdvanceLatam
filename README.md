# PicoPlacaAdvanceLatam
Ejercicio de prueba para el grupo Advance Latam


**Requisitos, tener instalado:
-maven
-docker

***Iniciando backend
Ingresar a la carpeta picoplaca-backend
+abrir un terminal
+ejecutar los siguientes comandos:
	-mvn package
	-docker build -t picoplaca:backend -f Dockerfile-app.txt .
	-docker run -it -p 8080:8080 --rm picoplaca:backend
luego de iniciarse el backend podrá visualizarse la bd desde http://localhost:8080/h2-console
con las siguiente configuracion
JDBC URL: jdbc:h2:mem:advancelatam
Username: as
Password: password



****Iniciando fronted
Ingresar a la carpeta picoplaca-frontend
abrir una consola
ejecutar los siguientes comandos:
docker build -t picoplaca:frontend -f Dockerfile.txt .
docker run -it -p 3000:3000 --rm picoplaca:frontend

Ingresar a la url http://localhost:3000/#
