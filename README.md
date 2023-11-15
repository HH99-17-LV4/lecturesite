# 항해99

협업 관리

# A.gitignore

대표적으로 
application-local.yml
application-secret.yml
파일들은 gitignore에 추가되어있어야함, init commit에 gitignore도 추가한 채로 할 수 있도록 하자



# B. 파일을 잘못 commit한 경우
  

### B-1. 잘못 commit한 파일을 삭제하는 commit을 수행하기
``` bash
git rm --cached <file_name> # 경로까지 입력해야함에 주의
git commit -m '잘못 commit한 파일 삭제' # 커밋 기록이 남는다.
```

  
위의 명령어로 해결하면 커밋 기록이 "기능 커밋", "잘못 commit한 파일 삭제" 와 같이 두개가 남는다.

### B-2. 해당 commit을 취소하고 싶은 경우 (가장 최근의 커밋인 경우)

``` bash
git reset --soft HEAD~1
```
  
이렇게 하면 commit하지않은 로컬 변경사항을 그대로 유지하며 마지막 커밋 기록을 삭제한다.
** 어떤 경우든 기록이 삭제되는 것은 권장되지 않으므로, 예를 들면 10개 짜리 커밋을 1개로 커밋 메세지 . 으로 커밋한 경우와 같이 심각할때만 사용 ** **

커밋 기록을 건드리는 행위이므로, 원격 브랜치의 커밋을 reset 하지 않도록 주의
** 정말 개인이 관리하고, 누군가 pull or push 할 일이 없는 원격 repo가 만약 있다면 상관없다. **
  

# 1. 브랜치 전략
  
master, dev, feat/{feature명}

# 1. 개발할 기능을 표현하는 이름으로 branch 생성

아래 명령어로 dev 브랜치에서 feature 브랜치로 분기,
  

```java
git branch #명령 후 현재 dev 브랜치에서 분기함을 확인 후 분기 
git checkout -b {feat/feature명}
```
  

- 참고 local branch만 분기된 것이지 remote와는 관계없음

# 2. 기능 개발 완료 후 add, commit, push하기

이때 원격 {feature명} branch가 생성된다.
  

# 3. Pull Request 보내기

내가 개발한 feature를 dev 브랜치에 merge를 하고싶다!

코드리뷰해줄 사람을 지정해서 확인 받은 후, 확인한 사람(or 관리자)가 merge 해줄 것,

  

### 3-1. Pull Request 보낸 후 주의

해당 브랜치가 Merge되기 전 새로 commit 하지 않는다.
(만약 기능이 완성되어 새로 commit 한 부분이 있다면 알려주세요)

  


# 4. Merge 되고 난 후

dev 브랜치에 merge 되고 난 후에는 새로운 일을 받아서 다시 새로운 feature 브랜치를 dev 브랜치에서 분기해서 작업할 것,

따라서 현재 로컬 {feature명} 브랜치와 remote {feature명} 브랜치는 필요가 없다.

```java
git branch -d {feature명} // 잘 안되면 -D 옵션으로
git push origin -d {feature명} // 원격 브랜치가 필요없는 경우만!!! 원격 브랜치 삭제
```

**경우에 따라 해당 브랜치에서 작업할 것이 남아있지만, 협업을 위해 중간에 merge한 상황이 된 경우에도 그냥 브랜치를 새로 분기하는게 좋습니다.

# 5. Merge를 승인하는 입장인 경우
  
  대부분의 경우 Intellij에서 conflict를 해결할 수 없다. (왜 그런지는 모름)
  가능하다면 Github pull request 페이지에서 resolve conflict로 해결하고, 이것도 불가하다면
  bash 명령으로 해결
#  **Merge**

일반적으로 많이 사용되는 병합이며, 커밋 이력을 모두 남길 때 사용한다.

`$ git checkout main
$ git merge my-branch`

## Squash & Merge : feature에서 dev로, 자잘한 커밋 로그를 남길 필요 없는 경우

Squash는 여러개의 커밋을 하나의 커밋으로 합치는 것을 의미한다. Squash Merge는 병합할 브랜치의 모든 커밋을 하나의 커밋으로 Squash한 새로운 커밋을 Base 브랜치에 추가하는 방식으로 병합하는 것을 의미한다.

Squash를 하게 되면 모든 커밋 이력이 하나의 커밋으로 합쳐지며 사라진다는 점을 주의해야한다.

```java
$ git checkout master
$ git merge --squash my-branch
$ git commit -m "squash & merge"
```

## rebase & merge 커밋 로그를 남기며 merge 해야 하는 경우

```bash
$ git checkout my-branch
$ git rebase main
$ git checkout main
$ git merge my-branch
```
