#!/bin/sh

echo "This script lists the connected repositories and allows you to connect and disconnect them."
echo "If the repository is connected, then entering its name will disconnect it"
echo "If the repository is disconnect, then entering its name will connected it"
echo ""
echo "Current repositoryes:"

command=1
error=0

while [ "$command" -eq "1" ]
do
  yum repolist all
  echo "Specify the repository to switch: "
  read reponame
  
  result=$(yum repoinfo "$reponame" | grep repolist | cut -d: -f2)
  echo $result
  if [ "$result" -ne "0" ]
  then
    error=0
    stat=$(yum repoinfo "$reponame" | grep "Repo-status" | cut -d: -f2)
    if [ "$stat" == "enabled" ]
    then
      yum update -y --enablerepo=$reponame
    else
      yum update -y --disablerepo=$reponame
    fi
  else
    error=1
    echo  "No sach repositories"
  fi
  echo "Repeat? (Yes - 1, No - 0)"
  read command
done

if [ -z "$error" ]
then
  echo
  exit 1
else
  echo
  exit 0
fi