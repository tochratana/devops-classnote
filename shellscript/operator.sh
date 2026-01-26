result=$(echo "scale=2;10 / 3" | bc) # take 0.00 when use scale
echo "$result"



# array : 
array=(1 2 3 4)
echo "$array"


for e in "${array[@]}"; do
  echo -n "$e";
