# Yakuza Battle

## Descripción del Proyecto

Yakuza Battle es un videojuego RPG por turnos desarrollado en Java utilizando Programación Orientada a Objetos y una interfaz gráfica construida con Java Swing. En el juego, el jugador controla a un miembro de la Yakuza que debe enfrentarse a diferentes policías para ascender dentro de la organización y convertirse en el líder principal.

El proyecto fue desarrollado como parte del curso de Programación Orientada a Objetos, aplicando conceptos fundamentales como encapsulamiento, herencia, polimorfismo y abstracción. Además, el sistema incluye mecánicas como combate por turnos, inventario de armas, sistema de curación, tienda, progresión de niveles y almacenamiento de puntuaciones.

Inicialmente el juego fue desarrollado en consola, pero posteriormente evolucionó hacia una versión gráfica utilizando Java Swing, mejorando la experiencia visual y la interacción con el usuario.

---

# Características Principales

* Sistema de combate por turnos
* Diferentes armas con daños aleatorios
* Sistema de curación y esquives
* Tienda para comprar armas y consumibles
* Interfaz gráfica desarrollada con Swing
* Sistema de puntuaciones Top 5
* Progresión de niveles
* Organización modular mediante paquetes

---

# Tecnologías Utilizadas

* Java
* Java Swing
* Programación Orientada a Objetos (POO)
* IntelliJ IDEA
* Archivos TXT para persistencia de datos

---

# Estructura del Proyecto

```bash
src/
├── armas/
├── curas/
├── personajes/
├── interfaces/
├── juego/
├── gui/
└── utils/
```

---

# Instalación y Ejecución

## Requisitos

Antes de ejecutar el proyecto es necesario tener instalado:

* Java JDK 17 o superior
* IntelliJ IDEA o cualquier IDE compatible con Java

---

## Clonar el repositorio

```bash
git clone https://github.com/JuanManuel0417/Juego-Yakuza.git
```

---

## Abrir el proyecto

1. Abrir IntelliJ IDEA
2. Seleccionar "Open Project"
3. Buscar la carpeta del proyecto
4. Esperar a que el IDE cargue las dependencias

---

## Ejecutar el juego

Ubicar el archivo:

```bash
Main.java
```

Y ejecutar el método principal (`main`).

El juego iniciará automáticamente en modo gráfico utilizando Swing.

---

# Mecánicas del Juego

## Combate

El jugador puede:

* Atacar
* Usar curas
* Cambiar de arma
* Esquivar ataques
* Comprar objetos en la tienda

Los enemigos también pueden atacar o curarse dependiendo de la situación del combate.

---

## Sistema de Tienda

El jugador obtiene dinero al derrotar enemigos y puede utilizarlo para comprar:

* Armas
* Botiquines
* Vendas
* Objetos de recuperación

---

## Sistema de Puntajes

Al finalizar la partida, el juego calcula un puntaje dependiendo del nivel alcanzado y el dinero acumulado. Las mejores puntuaciones se almacenan en un Top 5 utilizando archivos de texto.

---

# Aplicación de Programación Orientada a Objetos

El proyecto implementa los cuatro pilares de la Programación Orientada a Objetos:

## Encapsulamiento

Uso de atributos privados y métodos públicos de acceso.

## Herencia

Jerarquías como:

* Personaje → Yakuza / Policia
* Arma → Katana / Pistola / Glock

## Polimorfismo

Uso de referencias generales para manejar distintos tipos de personajes.

## Abstracción

Uso de clases abstractas e interfaces para definir comportamientos generales.

---

# Posibles Mejoras Futuras

* Agregar más enemigos y niveles
* Implementar música y efectos de sonido
* Añadir animaciones
* Mejorar la inteligencia artificial de los enemigos
* Implementar guardado de partidas
* Optimizar el sistema de inventario

---

# Autor / Equipo

Desarrollado por:
* Juan Manuel Gonzalez Mejia
* Esneider Andres Gonzalez Toscano

Institución: Pascual Bravo

Curso: Programación Orientada a Objetos — Java

Grupo: 050

Fecha: Mayo 2026
