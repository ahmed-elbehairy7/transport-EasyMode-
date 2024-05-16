@echo off

call compile.bat

if not errorlevel 1 call transport.bat %*