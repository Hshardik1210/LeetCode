You are given input files path in a array. Print them in order mentioned in output.

Input :
InputFiles=
[
"/app/home/file1.xml",
"/app/home/file2.xml",
"/app/temp",
"/home/temporary/file.txt",
"text1.xml",
"text2.html"
]

Output :

	--app
		--home
			--file1.xml
			--file2.xml
		--temp
	--home
		--temporary
			--file.txt
	--test1.xml
	--test2.xml