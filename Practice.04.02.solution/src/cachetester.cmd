@echo off
setlocal

:config
@rem specify the Coherence installation directory
set coherence_home=D:\Oracle\coherence\coherence
set java_home=D:\Oracle\jdk1.6.0_20

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

set java_opts="-Xms%memory% -Xmx%memory% -Dtangosol.coherence.ttl=0 -Dtangosol.coherence.cacheconfig=D:\Oracle\student\practices\Practice.04.02.solution\config\coherence-cache-config.xml"

"%java_exec%" -server -showversion "%java_opts%" -cp "%COHERENCE_CP%" CacheTester %1 %2 %3 %4

goto exit

:instructions

echo Usage:
echo   cachetester.cmd [-s startIndex -f finishIndex]
goto exit

:exit
endlocal
@echo on