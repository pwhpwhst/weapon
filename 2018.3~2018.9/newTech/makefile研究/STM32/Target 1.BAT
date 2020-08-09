SET PATH=F:\Keil_v5\ARM\ARMCC\Bin;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;F:\apache-maven-3.6.0\apache-maven-3.6.0\bin;F:\Java\jdk1.8.0_171\bin;F:\hadoop-2.8.3\hadoop-2.8.3\bin;F:\Program Files (x86)\mingw-w64\i686-7.3.0-posix-dwarf-rt_v5-rev0\mingw32\bin;F:\Program Files\MongoDB\Server\3.4\bin;C:\Program Files\Git\cmd;F:\Program Files\SmartGit\git\usr\bin;F:\mysql-5.7.22-winx64\mysql-5.7.22-winx64\bin;F:\apache-hive-2.3.4-bin\apache-hive-2.3.4-bin\bin;F:\vault_1.0.1_windows_amd64;F:\lua-5.3.4 win32\lua;F:\gradle-5.1.1-all\gradle-5.1.1\bin;F:\sqlite-tools-win32-x86-3280000;;G:\Program Files\Docker Toolbox
SET CPU_TYPE=STM32F407ZGTx
SET CPU_VENDOR=STMicroelectronics
SET UV2_TARGET=Target 1
SET CPU_CLOCK=0x00B71B00
"F:\Keil_v5\ARM\ARMCC\Bin\ArmCC" --Via ".\obj\main.__i"
"F:\Keil_v5\ARM\ARMCC\Bin\ArmAsm" --Via ".\obj\startup_stm32f40_41xxx._ia"
"F:\Keil_v5\ARM\ARMCC\Bin\ArmLink" --Via ".\Obj\Pwh.lnp"
"F:\Keil_v5\ARM\ARMCC\Bin\fromelf.exe" ".\Obj\Pwh.axf" --i32combined --output ".\Obj\Pwh.hex"
