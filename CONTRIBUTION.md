#Las razones de estas convenciones:
##Tener un registro sobre los cambios generados.

#Formato del mensaje de confirmaci�n:

##<type> <subject>
##en blanco
##<body>
##en blanco
##<footer>

#Ejemplo de mensaje de confirmaci�n:
##docs: Modificaci�n de requerimientos.

##Se realizaron cambios de los requerimientos por nueva l�nea de desarrollo.

##close #123 de acuerdo a las historias de usuario  e insection del GitHub.

#Asunto del Mensaje

##La primera l�nea no puede tener m�s de 20 caracteres
#<type>Valores permitidos 

#feat (nueva caracter�stica para el usuario, no una nueva caracter�stica para el script de compilaci�n)

#correcci�n (correcci�n de errores para el usuario, no una correcci�n para un script de compilaci�n)

#docs (cambios a la documentaci�n)

#estilo (formateo, puntos y comas que faltan, etc., sin cambio de c�digo de producci�n)

#refactor (c�digo de producci�n de refactorizaci�n, por ejemplo, cambio de nombre de una variable)

#prueba (agregando pruebas faltantes, pruebas de refactorizaci�n, sin cambio de c�digo de producci�n)

#Chore (actualizando tareas grunt, etc; sin cambio de c�digo de producci�n)

#Cuerpo del mansaje

##se usa el imperativo, tiempo presente: "modificaci�n"
##motivaci�n del cambio y fallas en la versi�n anterior

#Pie de p�gina
## Los problemas cerrados se enumeran en una l�nea separada en el pie de p�gina
## close #1234

Ejemplo:

Chore: Carpeta de requerimientos 

Se agreg� la carpeta de requerimientos del proyecto (DWBH-CORPORATION)

Close: #100

Las carpetas del proyecto se subir�n en el siguiente orden:
Se enumeraran as�  0.0 - Nombre de la carpeta.
