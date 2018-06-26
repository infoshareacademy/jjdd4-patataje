#!/bin/bash

mkdir utf8/

find ./ -name "*.csv" -type f |
while read file
do
  echo " $file"
  iconv -f WINDOWS-1250 -t UTF-8 $file > utf8/$file
done
