# Proyecto: Zombie Survivor

Este proyecto es una implementación de un juego de supervivencia en un mundo postapocalíptico, donde el jugador debe moverse estratégicamente en un mapa para sobrevivir a diferentes retos y llegar a un lugar seguro.

| Detalle               | Información       |
|-----------------------|-------------------|
| **Autor**            	| GSC               |
| **Fecha de inicio**   | 27/12/2024        |
| **Última revisión**   | v1.8 28/04/2025   |

## Contenido del Proyecto

- **Versión 1 (`ZombieSurvivor.java`)**: Creación de la base del videojuego con un menú y controles de errores.
- **Versión 1.1 (`ZombieSurvivor.java`)**: Otra creación de otro juego nuevo ya que el otro lo borré y en este tiene lo mismo solo que hay funcionalidad para jugar y aparte he añadido arrays.
- **Versión 1.2 (`ZombieSurvivor.java`)**: He utilizado la modularización en el código.
- **Versión 1.3 (`ZombieSurvivor.java`)**: Actualización de la documentación, fichero readme y he añadido comentarios encima de cada método
- **Versión 1.4 (`ZombieSurvivor.java, Personaje.java, Jugador.java, Zombie.java, Mapa.java, Lugar.java`)**: He dividido el fichero donde está la main en varios ficheros y he incrulido herencia
- **Versión 1.5 (`ZombieSurvivor.java, Personaje.java, Jugador.java, Zombie.java, Mapa.java, Lugar.java`)**: No entregada ya que era para los que tenian que recuperar
- **Versión 1.6 (`ZombieSurvivor.java, Juego.java, Personaje.java, Jugador.java, Zombie.java, Mapa.java, Lugar.java, Utilidades.java`)**: Creación del fichero Juego.java para quitar contenido de la main y fichero Utilidades para leer los datos introducidos por el usuario.
- **Versión 1.7 (`Main.java, ZombieSurvivor.java, Juego.java, Partida.java, Utilidades.java, Personaje.java, Jugador.java, Zombie.java, Mapa.java, Lugar.java`)**: Creación del fichero Partida y corrección de algunos aspectos a mejorar en la mayoría de los ficheros
- **Versión 1.8 (`Main.java, ZombieSurvivor.java, Juego.java, Partida.java, Utilidades.java, Personaje.java, Jugador.java, Zombie.java, Mapa.java, Lugar.java, ConexionBBDD.java`)**: Creación del fichero ConexionBBDD para conectarse a ella y poder almacenar los datos de la partida en la BBDD

## Estructura del Proyecto

```plaintext
ZombieSurvivor_project/
    ZombieSurvivor_src/
        README.md           # Este archivo
		ZombieSurvivorMain.jar  # .jar para ejecutar el juego desde cualquier lugar
		Partidas.txt		# Fichero en el que se almacenan los datod de la partida
        .vscode/            # Configuraciones para Visual Studio Code
            settings.json
		bin/				# Carpeta para guardar el .class
			Main.class
			bbdd/
				ConexionBBDD.class
			mapa/
				Mapa.class
				Lugar.class
			personaje/
				Personaje.class
				Jugador.class
				Zombie.class
			utilidades/
				Utilidades.class
			zombieSurvivor/
				ZombieSurvivor.class
				Partida.class
				Juego.class
        lib/                # Carpeta vacía
			mysql-connector-j-8.0.33.jar
        src/                # Carpeta con el código fuente
			Main.java
			bbdd/
				ConexionBBDD.java
            mapa/
				Mapa.java
				Lugar.java
			personaje/
				Personaje.java
				Jugador.java
				Zombie.java
			utilidades/
				Utilidades.java
			zombieSurvivor/
				ZombieSurvivor.java
				Partida.java
				Juego.java
```

## Requisitos del Sistema

- Java Development Kit (JDK) versión 21 o superior.
- MySQL instalado y en ejecución (la base de datos zombiesurvivor debe estar creada).
- Entorno de desarrollo recomendado: Visual Studio Code.
- Sistema operativo: Windows (para compatibilidad con colores en consola y rutas del proyecto).

## Instrucciones de Ejecución

1. Abre el proyecto en **Visual Studio Code**.
2. Asegúrate de que JDK 21 esté correctamente configurado.
3. Ve a la carpeta `src/` y selecciona el archivo que deseas ejecutar `ZombieSurvivorMain.java`.
4. Compila y ejecuta el programa:
	javac ZombieSurvivorMain.java
	java ZombieSurvivorMain

- Descarga y descomprime el archivo ZombieSurvivor_GuillermoSierra1-8.zip.
- Abre la carpeta descomprimida en Visual Studio Code.
- Asegúrate de que el JDK 21 esté configurado correctamente en tu entorno.
- Verifica que la base de datos MySQL esté activa y que la tabla partidas exista en la BBDD zombiesurvivor.
- Ve a la carpeta src/ y ejecuta el archivo Main.java, que es el punto de entrada del juego.
- Si quieres ejecutar vía cmd tienes que ir a la ruta donde se encuentra el .jar y ejecutar --> java NombreFicheroJar.jar


## Funcionalidades Principales

- **Mapa dinámico**: El juego genera un tablero de 3x3 con diferentes ubicaciones, algunas de las cuales afectan la vida y energía del jugador.
- **Movimientos estratégicos**: Permite al jugador desplazarse en ocho direcciones o salir de la partida.
- **Gestión de recursos**: La vida y la energía  del jugador varían según las ubicaciones a las que decida ir ya que quitan o suman vida y energía.
- **Condiciones de victoria y derrota**: El jugador gana si alcanza el "Lugar seguro" con vida y energía suficientes. La partida se marca como ganada o perdida dependiendo de los eventos ocurridos.
- **Coloración visual**: Los mensajes del juego usan colores para destacar información importante.
- **Sistema de combate y enemigos**: Algunos lugares contienen zombies que reducen la vida y energía del jugador. Se pueden generar entre 1 y 3 zombies por ubicación no segura.
- **Registro de partida y estadísticas**: Se guarda la fecha de inicio y fin de la partida. Se lleva un registro del número de movimientos realizados. Indica si la partida fue ganada o perdida.
- **Historial de partidas desde archivo**: Todas las partidas jugadas se guardan en un fichero Partidas.txt ubicado en la raíz del proyecto. Este fichero incluye estadísticas clave como nombre del jugador, fecha de inicio y fin, movimientos, resultado y duración.
- **Base de datos MySQL integrada**: Además del guardado en fichero, el juego registra automáticamente las partidas en una tabla llamada partidas, dentro de la base de datos zombiesurvivor. Esto permite una visualización más avanzada y persistente del historial.
- **Visualización personalizada del historial**: Desde el menú principal puedes ver todas las partidas o filtrar por nombre de jugador. Esto aplica tanto a los registros guardados en fichero como a los almacenados en la base de datos.
- **Modularización real del proyecto**: El código fuente está dividido en paquetes organizados por responsabilidad (mapa, personaje, zombiesurvivor, utilidades, bbdd), y esto facilita su mantenimiento y evolución. Esta estructura está completamente visible al descomprimir el .zip.
- **Ejecución directa o por consola**: Puedes compilar y ejecutar fácilmente desde Visual Studio Code o utilizar comandos desde consola para mayor control. El proyecto está preparado para ambas opciones.

## Notas Adicionales

- **Modularización**: Uso de métodos para organizar el código.
- **Manejo de excepciones**: Validaciones para entradas no válidas y errores del usuario.
- **Programación interactiva**: Creación de juegos con respuestas inmediatas para el jugador.
- **Herencia y POO**: La clase Personaje es padre de Jugador y Zombie.
- **Gestión del estado del jugador**: Métodos para modificar vida y energía en función de eventos.
- **Control de flujo del juego**: La clase Juego gestiona el desarrollo de la partida, movimientos y actualizaciones del mapa.
- **Estructura orientada a partidas**: Cada sesión de juego se maneja como una instancia de Partida.
- **Colores en consola**: Se utilizan códigos ANSI para mostrar mensajes en distintos colores que mejoran la experiencia del usuario.
- **Persistencia dual**: Registro de partidas tanto en un fichero local (Partidas.txt) como en una base de datos MySQL.
- **Visualización de historial**: Posibilidad de consultar partidas anteriores por consola, filtrando por jugador o viendo el historial completo.

Para crear el fichero.jar lo podemos hacer de 2 formas:

1. Dentro de Visual Studio Code vamos abajo a la izquierda que aparece Java Projects,
seleccionamos la main de la cual queremos crear el fichero.jar y le damos a export jar,
hecho esto seleccionamos donde guardarlo y ya estaría creado.

2. La segunda opción es mediante comandos con la consola de windows los cuales serían:
	2.1 Compilar el código Java
	Comando: javac MiArchivo.java

	2.2 Crear el archivo MANIFEST.MF
	Comando: notepad Manifest.mf
	Añadimos estas 2 líneas
	Manifest-Version: 1.0
	Main-Class: NombreClasePrincipal

	2.3 Crear el archivo JAR
	Comando: jar cfm MiArchivo.jar Manifest.mf MiArchivo.class

	2.4 Crear un archivo JAR ejecutable
	Comando: jar cfe MiArchivo.jar MiClasePrincipal MiArchivo.class 

	2.5 Verificar el contenido del archivo JAR
	Comando: jar tf MiArchivo.jar

	2.6 Ejecutar el archivo JAR
	Comando: java -jar MiArchivo.jar

Como lo hice en mi portátil:

C:\Users\Guillermo\Downloads\Juego>javac src/Adivina.java
C:\Users\Guillermo\Downloads\Juego>cd src
C:\Users\Guillermo\Downloads\Juego\src>move Adivina.class ../bin
C:\Users\Guillermo\Downloads\Juego\src>cd ../bin
C:\Users\Guillermo\Downloads\Juego\bin>notepad manifest.mf
	Manifest-Version: 1.0
	Main-Class: Adivina
	(Dentro del fichero manifest)

C:\Users\Guillermo\Downloads\Juego\bin>jar cfm juego.jar manifest.mf Adivina.class
C:\Users\Guillermo\Downloads\Juego\bin>jar cfe juego.jar Adivina Adivina.class
C:\Users\Guillermo\Downloads\Juego\bin>java -jar juego.jar (Ejecutar en powershell para verse con colores)

Una vez creado el jar lo puedo mover donde yo quiera