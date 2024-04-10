!!!!!!  탄지로 사용하시면 안됩니다 !!!!!


신들의 전쟁 플러그인 version 5.0    
===========================================
### Mincraft Version : 1.20.2 
* * *
#### 현재 작성자 :  프덧 ( foodust )

> 작성자들
> - 원작자 - 칠각별 ( septagram )
> - 두번째 수정자 - 플로리아 ( humint2003 )
> - 마지막 수정자 - 프덧 ( foodust )

* * * 
[ 사용 기반 소스 ] <https://github.com/Flair-Delta/Theomchy-Pi-Editon>
* * *
 
1.7.10 버전이었던 소스 코드를   
1.20.2 버전으로 업데이트   
그에 따른 소스 코드 대거 변경 및 로직 변경

현재 개발중임 
사용가능하나 버그가 많을 수 있음

Intelij 로 개발됨   
build.gradle 에서   
destinationDirectory 를 변경후 빌드 하시길 바람
```
jar{
    archiveFileName = 'Theomachy.jar'
    destinationDirectory = file("C:\\Users\\Admin\\Desktop\\게임\\server1.20.2\\plugins")
    manifest {
        attributes 'Main-Class': 'septagram.Theomachy.Theomachy'
    }
}
```


* * *
이하 아래는 패치노트임


==== 버전 ====

Minecraft 1.20.2

==== 소스 ====  
Java Version SE 1.7 -> 17   
소스 코드 가독성 증가   
소스 코드 버전 업   
소스 코드 로직 변경   
소스 코드 유지보수 확장성 변경   


==== 설명 ====   
스킬 지속시간 명시


==== 추가 ====

네더라이트 관련 추가 예정

팀 랜덤 추가

팀 스폰 지정 , 좌표로 가능

뽑기 로직 변경

셋팅 - 디버그 모드 추가

==== 능력 ====

[God]   
샌즈 추가

[주술회전]   
이타도리 유우지 추가   
죠고 추가   
료멘 스쿠나 추가   
고죠 사토루 추가

[귀멸의 칼날]   
아가츠마 젠이츠 추가   
렌고쿠 쿄주로 추가   
카마도 탄지로 추가  
토미오카 기유 추가  

[인간]   
메구밍 삭제

==== 변경 =====

아프로디테
- 랭크	| S -> A
- 일반	| 쿨타임 감소 500 -> 120
- 일반	| 조약돌 감소 64 -> 20

헤파이스토스
- 랭크	| B -> S
- 일반	| 지속시간 2 -> Inf
- 일반	| 쿨타임 증가 10 -> 30
- 일반	| 조약돌 증가 1 -> 15

아르테미스
- 스킬	| 일반/고급 -> 일반 , 고급

아이올로스
- 랭크	| S -> A

메테오
- 랭크	| S -> A
- 일반	| 쿨타임 감소 110 -> 100
- 일반	| 반경 변경
- 일반	| 딜레이 감소

스탠스
- 랭크	| A -> B

아카샤
- 고급	| 쿨타임 감소 120 -> 100

아처
- 이름	| 궁수 -> 아처
- 일반	| 쿨타임 감소 20 -> 10
- 일반	| 조약돌 감소 7 -> 5
- 스킬	| 일반/고급 -> 일반, 고급

크리퍼
- 랭크	| B -> S
- 일반	| 데미지 증가 3 -> 5
- 고급	| 추가  x -> 자신에게 번개 소환
- 고급	| 쿨타임 x -> 120
- 고급	| 조약돌 x -> 50
- 고급	| 데미지 증가 6 -> 10

다크니스
- 이름	| 다크니스 -> 탱커
- 랭크	| C -> A
- 패시브	| 공격 불가 -> 공격 가능
- 패시브	| 데미지 감소 1/10 -> 1/5

포케고
- 일반	| 추가 x - > 걸음 표시
- 일반	| 쿨타임 X
- 일반	| 조약돌 X

가솔린
- 랭크	| A -> B

반사
- 이름	| 반사 -> 가시갑옷
- 패시브	| 확률 제거

눈사람
- 이름	| 미친눈사람 -> 눈사람
- 패시브	| 기본 눈덩이 삭제
- 일반	| 지수 확인 가능
- 고급	| 추가 x -> 자신에게 눈덩이 3개 소환
- 고급	| 쿨타임 x -> 50
- 고급	| 조약돌 x -> 10

타짜
- 랭크	| S -> A
- 일반	| 매커니즘 변경

제트엔진
- 랭크	| S -> B
- 패시브	| 지속시간 5 -> 10

봄버
- 일반	| 데미지 2 -> 4

암살자
- 일반   | 쿨타임 증가 x -> 10
- 일반   | 조약돌 0 -> 1




