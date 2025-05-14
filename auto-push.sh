git add .
echo -n "[+] Commit message: "
read msg
git commit -m "$msg"
echo -n "[+] Insert branch name: "
read branch
git push -u origin $branch