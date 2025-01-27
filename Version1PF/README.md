# Configuración del entorno

## Requisitos
- **Java**: JDK 23.0.1 ([Descargar](https://jdk.java.net/23/))
- **Maven**: 3.9.9 ([Descargar](https://maven.apache.org/download.cgi))
- **Python**: 3.x + `venv`

## Configurar variables de entorno
1. **Java**:
   - Establece `JAVA_HOME` con la ruta de tu JDK.
   - Ejemplo (PowerShell):
     ```powershell
     $env:JAVA_HOME = "tu_ruta_jdk"
     $env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
     ```

2. **Maven**:
   - Establece `MAVEN_HOME` con la ruta de Maven.
   - Ejemplo (PowerShell):
     ```powershell
     $env:MAVEN_HOME = "tu_ruta_maven"
     $env:PATH = "$env:MAVEN_HOME\bin;$env:PATH"
     ```

3. **Entorno virtual de Python**:
   ```powershell
   python -m venv venv
   .\venv\Scripts\Activate.ps1
   pip install -r requirements.txt
   ```

## Ejecutar el proyecto
1. **Activar el entorno virtual de Python**:
   ```powershell
   .\venv\Scripts\Activate.ps1
   .\venv\Scripts\Activate-maven.ps1
   ```
## Ejecutar el proyecto por ejecucion de jar
1. **Ejecutar el jar**:
   ```powershell
   mvn clean compile assembly:single
   java -jar target/mi-proyecto-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

