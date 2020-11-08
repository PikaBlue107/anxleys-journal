# Run the CommentHunter program
gradle run
# Copy the generated index.html to the top level of the repo
cp src/main/resources/index.html ..
cd ..

# Add the new changes to the index, commit them, and report it
git status
git add index.html
git add YoutubeAPIGradle/src/main/resources/index.html
git status

# Check with the user if we want to push
read -r -p "Commit and push to GitHub? y to push, any other input to cancel: " doPush
if [[ ${doPush} == y ]]; then
	git commit -m "Update comment list"
	git push
else
	echo "Skipping push."
fi
echo "Run complete! [Enter] to close"
read