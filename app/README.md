# 🐾 AppVet 

Aplicación desarrollada en Android Studio para la gestión integral de pacientes veterinarios, contando con una arquitectura de navegación explícita ademas una robusta integración directa de servicios mediante intents implícitos.


## 📱 Descripción del Proyecto
** Está diseñada para optimizar el flujo de trabajo en clínicas veterinarias, permitiendo el control de pacientes, el registro de citas, la camara fotográfic y la conectividad directa con (mapas, llamadas, correo y calendario).

### 🧭 Navegación Estructurada (Intents Explícitos)
* Pantalla de Inicio / Login: Validación segura de usuarios.
* Dashboard Principal:Panel centralizado de control y gestión.
* Formulario y Detalle: Registro y visualización de fichas de pacientes.
* Confirmación de Citas: Módulo de validación de procesos clínicos.

### 🔌 Integración (Intents Implícitos)
* 🗺️ Ubicación (`ACTION_VIEW`): Consulta la ruta y ubicación de la clínica en maps.
* 📞 Urgencias (`ACTION_DIAL`): Marca directamente al número de emergencia.
* ✉️ Consultas (`ACTION_SENDTO`): Envío rápido de reportes mediante el cliente de correo.
* 📷 Camara (`ACTION_IMAGE_CAPTURE`): Captura imágenes de los pacientes.
* 📅 Agenda (`ACTION_INSERT`): Sincronización de vacunas con el calendario del dispositivo.


## 📸 Camara
<p align="center">
  <img src="file:///C:/Users/belu/Downloads/AppVet/AppVet/capturas/mapa.jpeg" width="300"/>
  <br><b>Mapa / Ubicación</b>
</p>

<p align="center">
  <img src="file:///C:/Users/belu/Downloads/AppVet/AppVet/capturas/calendario.jpeg" width="300"/>
  <br><b>Calendario / Agenda</b>
</p>

<p align="center">
  <img src="file:///C:/Users/belu/Downloads/AppVet/AppVet/capturas/menu.jpeg" width="300"/>
  <br><b>Menú Principal</b>
</p>
## 🛠️ Tecnologías y Arquitectura
* Lenguaje: Java / Kotlin
* Entorno de Desarrollo: Android Studio
* Componentes: Activities, Intents (Explícitos e Implícitos), Manejo de Excepciones (`try-catch`), Diálogos dinámicos.



## 🎓 Lecciones Aprendidas & Retos
* Manejo de Permisos y Excepciones:Se implementaron bloques de seguridad robustos (`try-catch`) para evitar cierres forzosos al invocar servicios directos que no estuvieran en el dispositivo.
* Experiencia de Usuario (UX):Diseño de interfaces, accesibles y orientadas a la optimización de tiempos en entornos clínicos reales.


*Desarrollado con ❤️ para el curso de Programacion android por Anghel y Allison.*