# hello_world 
создаем сначала в каталоге Hello_World папку bin, чтобы бинарные файлы сохранялись в отдельную папку, 
а не лежали с исходниками
командой  javac -d bin src/Hello_World.java   мы компилируем
команда java -classpath ./bin Hello_World     запускает программу
это все выполняется при запуске start.bat
#это проект мавен
#собирается командой "mvn package"
#запускается командой "java -jar taskmaven-1.0.jar"
>>>>>>> tasks/maven
