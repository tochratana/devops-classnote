#!/bin/bash

echo "=============================="
echo " Server Performance Stats"
echo "=============================="

echo ""
echo "OS Version:"
cat /etc/os-release | grep PRETTY_NAME | cut -d= -f2 | tr -d '"'

echo ""
echo "Uptime:"
uptime -p

echo ""
echo "CPU Usage:"
CPU_IDLE=$(top -bn1 | grep "Cpu(s)" | awk '{print $8}')
CPU_USAGE=$(echo "100 - $CPU_IDLE" | bc)
echo "Total CPU Usage: $CPU_USAGE%"

echo ""
echo "Memory Usage:"
free -h
MEM_TOTAL=$(free | awk '/Mem:/ {print $2}')
MEM_USED=$(free | awk '/Mem:/ {print $3}')
MEM_PERCENT=$(awk "BEGIN {printf \"%.2f\", ($MEM_USED/$MEM_TOTAL)*100}")
echo "Memory Used: $MEM_PERCENT%"

echo ""
echo "Disk Usage:"
df -h --total | grep total

echo ""
echo "Top 5 Processes by CPU:"
ps -eo pid,comm,%cpu,%mem --sort=-%cpu | head -n 6

echo ""
echo "Top 5 Processes by Memory:"
ps -eo pid,comm,%cpu,%mem --sort=-%mem | head -n 6

echo ""
echo "Load Average:"
uptime | awk -F'load average:' '{ print $2 }'

echo ""
echo "👥 Logged in Users:"
whoami

echo ""
echo "Failed Login Attempts:"
if command -v lastb &> /dev/null; then
    sudo lastb | head -n 5
else
    echo "lastb command not available"
fi

echo ""
echo "✅ Done"