# library

항해  17기 spring lv2 과제

작업 프로세스

# 1. 개발할 기능을 표현하는 이름으로 branch 생성

아래 명령어로 dev 브랜치에서 feature 브랜치로 분기,

``` bash
git branch {feature명}
git switch {feature명}
```

- 참고 local branch만 분기된 것이지 remote와는 관계없음

# 2. 기능 개발 완료 후 add, commit, push하기

이때 원격 {feature명} branch가 생성된다.

# 3. Pull Request 보내기

내가 개발한 feature를 dev 브랜치에 merge를 하고싶다!

코드리뷰해줄 사람을 지정해서 확인 받은 후, 확인한 사람(or 관리자)가 merge 해줄 것,


### 3-1. Pull Request 보낸 후 주의

해당 브랜치가 Merge되기 전 새로 commit 하지 않는다.


# 4. Merge 되고 난 후

dev 브랜치에 merge 되고 난 후에는 새로운 일을 받아서 다시 새로운 feature 브랜치를 dev 브랜치에서 분기해서 작업할 것,

따라서 현재 로컬 {feature명} 브랜치와 remote {feature명} 브랜치는 필요가 없다.

``` bash
git branch -d {feature명} // 잘 안되면 -D 옵션으로
git push origin -d {feature명} // 원격 브랜치가 필요없는 경우만!!! 원격 브랜치 삭제
```

# 5. Merge를 승인하는 입장인 경우

# **Merge**

일반적으로 많이 사용되는 병합이며, 커밋 이력을 모두 남길 때 사용한다.

`$ git checkout main
$ git merge my-branch`

## Squash & Merge : feature에서 dev로, 자잘한 커밋 로그를 남길 필요 없는 경우

Squash는 여러개의 커밋을 하나의 커밋으로 합치는 것을 의미한다. Squash Merge는 병합할 브랜치의 모든 커밋을 하나의 커밋으로 Squash한 새로운 커밋을 Base 브랜치에 추가하는 방식으로 병합하는 것을 의미한다.

Squash를 하게 되면 모든 커밋 이력이 하나의 커밋으로 합쳐지며 사라진다는 점을 주의해야한다.

```  bash
$ git checkout main
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

# 6. 현재 feat/{featuer명} 에서 작업중이고, dev가 변경되었다면, feat 브랜치에서 pull dev 하고나서 진행해도 된다.