[//]: # (常用GitBash操作如下：)
[//]: # (当你在 Git Bash 中克隆了一个远程仓库到本地，并对本地文件进行了修改后，想要将这些修改提交并上传到远程仓库，可以遵循以下步骤：)
[//]: # (检查状态： 首先，使用 git status 命令查看哪些文件被修改了，哪些文件是新增的，以及哪些文件还没有被 Git 跟踪。)
[//]: # (添加文件： 使用 git add 命令将修改或新增的文件添加到暂存区。如果你想添加所有修改过的文件，可以使用：)
[//]: # (Code)
[//]: # (git add .)
[//]: # (如果只想添加特定文件，可以用：)
[//]: # (Code)
[//]: # (git add <file1> <file2> ...)
[//]: # (提交更改： 使用 git commit 命令将暂存区的更改提交到本地仓库。记得附上描述性消息说明本次提交做了什么变动：)
[//]: # (Code)
[//]: # (git commit -m "描述性提交信息")
[//]: # (拉取最新代码： 在推送之前，建议先执行 git pull 来获取远程仓库的最新代码，以避免潜在的合并冲突：)
[//]: # (Code)
[//]: # (git pull origin <branch_name>)
[//]: # (这里 <branch_name> 是你当前所在的分支名，通常为 main 或 master，也可能是其他自定义的分支名。)
[//]: # (推送更改： 最后，使用 git push 命令将本地的提交推送到远程仓库对应的分支上：)
[//]: # (Code)
[//]: # (git push origin <branch_name>)
[//]: # (如果是第一次推送某个新分支到远程仓库，可能需要指定 -u 参数来建立追踪关系：
建立追踪关系：当你首次使用 -u 推送一个本地分支到远程仓库的一个分支时，Git会为你的本地分支设置一个上游(upstream)分支。
这意味着Git会记住你这个本地分支(master在这个例子中)对应的远程分支是哪个(origin/master)。这样一来，之后当你在该本地
分支上执行 git pull 或者 git push 而不指定远程仓库和分支名时，Git会自动使用之前设定的上游分支作为默认目标。简化后续
命令：一旦建立了追踪关系，未来你只需要输入 git push 而不需要每次都指定 origin master，Git就会知道你要将本地的 master 分支推送到远程的 origin/master。
同样，git pull 也会默认从对应的上游分支拉取更新。)
[//]: # (Code)
[//]: # (git push -u origin <branch_name>)
[//]: # (确保在执行这些命令前，你已经配置好你的 Git 用户名和邮箱，这是通过 git config 命令完成的。如果尚未配置，可以使用如下命令进行设置：)
[//]: # (Code)
[//]: # (git config --global user.name "你的用户名")
[//]: # (git config --global user.email "你的邮箱")
[//]: # (完成以上步骤后，你的本地修改就会被提交并上传到远程仓库了。)
此项目是支持于客户华泰某部门的实时开发测试代码，目前涉及到FlinkCdc。。。。。！
