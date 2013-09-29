::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::
:: This is a hack to run Apache Ant. It uses dcp's hardwired environment.
:: Change the environment variables to suit your environment.
::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set HOME=E:\work\Java\ponglegacy
set JAVA_HOME=C:\Program Files\Java\jdk1.6.0_14
set ANT_HOME=X:\Math\Petty\apcs\apache-ant-1.8.1

%ANT_HOME%\bin\ant.bat -buildfile %HOME%\build.xml %1 %2 %3 %4 %5 %6 %7 %8
