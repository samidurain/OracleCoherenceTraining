@echo off
setlocal

:config
@rem specify the Coherence installation directory
set coherence_home=C:\Oracle\Middleware\Oracle_Home\coherence\
set java_home=C:\java\jdk1.7.0_51


@rem specify the JVM heap size
set memory=64m

set SUPPORT="..\..\..\support"
set COHERENCE_CP=..\bin
set COHERENCE_CP=%COHERENCE_CP%;..\..\DomainExamples\bin
set COHERENCE_CP=%COHERENCE_CP%;%SUPPORT%\coherence-tools-1.0.jar
set COHERENCE_CP=%COHERENCE_CP%;%SUPPORT%\commons-logging.jar
set COHERENCE_CP=%COHERENCE_CP%;%SUPPORT%\log4j-1.2.15.jar
set COHERENCE_CP=%COHERENCE_CP%;%coherence_home%\lib\coherence.jar

:start
if not exist "%coherence_home%\lib\coherence.jar" goto instructions

if "%java_home%"=="" (set java_exec=java) else (set java_exec=%java_home%\bin\java)


:launch

set java_opts="-Xms%memory% -Xmx%memory% -Dtangosol.coherence.ttl=0 -Dtangosol.coherence.cacheconfig=C:\Oracle\Middleware\Oracle_Home\coherence\lib\coherence\coherence-cache-config.xml -Dtangosol.coherence.override=C:\Oracle\Middleware\Oracle_Home\coherence\lib\coherence\config\tangosol-coherence-override-dev.xml"

"%java_exec%" -server -showversion "%java_opts%" -cp "%COHERENCE_CP%" CacheTester %1 %2 %3 %4

goto exit

:instructions

echo Usage:
echo   cachetester.cmd [-s startIndex -f finishIndex]
goto exit

:exit
endlocal
@echo on