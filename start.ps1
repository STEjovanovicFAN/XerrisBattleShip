Write-Host "~Compiling java code~`n"
javac *.java

Write-Host "~Executing java code~`n"
java Main

Write-Host "~Cleaning java .class files~`n"
Get-ChildItem *.class -Recurse | foreach { Remove-Item -Path $_.FullName }

Write-Host "~Exiting script~`n"