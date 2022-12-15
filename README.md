# 클린시티 (Clean City)

## 📌 주제
AI 서비스를 활용한 퍼스널 모빌리티(Personal Mobility) 주차문제 해결

## 📌 Personal Mobility 란
전기를 동력으로 하는 1인용 이동수단으로 전동 휠, 전동 킥보드, 전기 자전거, 초소형 전기차 등이 포함된다.

## 📌 프로젝트 개요
- 지역 경관을 해치는 요소 그중에서도 자연과 직결된 퍼스털 모빌리티
- 퍼스널 모빌리티 관련 민원은 2019년부터 연간 1,900건을 넘어서며 도시의 골칫거리가 되었다. 특히 전동 킥보드 주정차위반사례의 45.5%가 '보행자통행방해'를 일으켰고 '자동차 등 통행방해'가 21.1%에 해당한다.
- 인도와 차도, 보행자와 운전자를 가릴 것 없이 모두에게 걸림돌이자 위험요소가 되는 퍼스널 모빌리티의 주차 문제를 해결하는 것이 지역 내 시급한 안건이다.

## 📌 서비스 요약
<img src="https://user-images.githubusercontent.com/72978589/207209102-f0aa2128-d757-42b2-94f0-c25d0d22c04e.png" width="60%" height="20%">    

## 📌 Personal Mobility 시장분석
### ✅ 코로나19 이후, 개인형 이동수단 수요 증가
<img src="https://user-images.githubusercontent.com/72978589/207209383-3d055866-8196-41b1-9e8e-c4a0f4bb8576.png" width="40%" height="20%">   

### ✅ Personal Mobility 개인형 이동수단 SWOT 분석
<img src="https://user-images.githubusercontent.com/72978589/207209906-1a7d78c2-6c13-4da4-a9dd-cf700cf0fea8.png" width="70%" height="50%">   

## 📌 Personal Mobility 주차 문제
<img src="https://user-images.githubusercontent.com/72978589/207210634-89b8372e-7cf3-4288-939c-945ee4dcf25a.png" width="70%" height="60%">   

### ✅ 해결을 시도한 사례들
- `수거 용역업체(마포구)`   
&rarr; 도로교통법 규정 5개 구역* 외에는 무단 주정차 임의로 수거 불가
- `서울시 전동킥보드 전용 주차장 설치`  
&rarr; 송파구, 서초구, 마포구, 노원구에서 총 50여개 운영, 실제로 어디 있는지 찾기 어렵다는 시민 의견 다수
- `'지쿠터'현장 운영팀 출동`   
&rarr; 헬멧 분실, 불법 주정차시 현장 출동, 현장 운영팀의 순찰만으로는 한계가 있음
- `서울시 구청-모빌리티 업체 핫라인 구축`   
&rarr; 국내업체는 효과 있으나 글로벌업체는 관리와 소통 소홀하여 해결 어려움

### ✅ 시사점
무단 주정차 발생시 바로 신고하고 처리할 수 있는 서비스 필요

## 📌 문제 정의
### ✅ 기존의 해결책과 새로운 의견
구청과 퍼스널 모빌리티 업체에서 여러차례 문제 해결을 시도했다. 공통된 점은 무단주차된 킥보드나 자전거를 시고하면 반드시 업체의 관계자나 구청 관계자가 출동하여 해결하는 것이었다. 이 방식은 신고 접수부터 처리까지 다소 시간이 소요되기 때문에 효과적인 해결방법이 아니었다.  
따라서 통행에 방해가 되는 이동수단을 발견하는 즉시 업체로 신고 정보를 보내고 사용자가 직접 적당한 곳으로 옮기도록 업체에서 일시 잠금 해제를 하는 기능을 추가하고, 인근 현장운영팀이 바로 출동할 수 있는 서비스를 구상하였다. 
<img src="https://user-images.githubusercontent.com/72978589/207935948-2e8319db-868a-4671-9145-60bbc586d696.png" width="40%" height="20%">   

## 📌 핵심 아이디어
<img src="https://user-images.githubusercontent.com/72978589/207936331-bd01c207-afec-40eb-9f0b-d191983f8289.png" width="80%" height="20%">

## 📌 기획 의도
통행을 방해하는 Personal Mobility(이 프로젝트에서는 킥보드와 자전거만 해당)를 사용자가 AI 서비스로 직접 신고함으로써, 안전하고 쾌적한 도시 환경을 만든다.

## 📌 기대 효과
<img src="https://user-images.githubusercontent.com/72978589/207212633-d44c5e28-ae88-4aa9-80ec-f0f882202499.png" width="70%" height="70%">   

## 📌 어플리케이션 핵심 기능
- 현재 내 위치의 위도와 경도 값을 가져온다.
- 킥보드 or 자전거 카테고리를 선택한다.
- 사진을 찍으면 Naver AI Services CLOVA OCR를 통해 인쇄물 속 글자를 추출하여 퍼스널 모빌리티의 회사명을 추출한다.
- 처리 방법을 선택하면, 신고 날짜, 시간, 위치(경도, 위도), 회사명, 처리 방법을 반환한다.

## 📌 AI 서비스
### ✅ OCR (Optical Character Recognition): 광학문자인식 기술
- 인쇄물이나 이미지 속 문자를 추출하여 디지털 데이터로 변환해주는 자동인식 솔루션
- OCR을 이용하면 복잡한 양식의 문서나 레이아웃에서도 이미지 조정과 굴곡 보정을 통해 지정된 영역 내부에서 필요한 문자만을 추출할 수 있다.
<img src="https://user-images.githubusercontent.com/72978589/207937219-0ce9585e-2b1a-4d3e-a2a4-ffa13c8ac8de.png" width="80%" height="15%">  
- OCR 기술의 활용: `문서 처리 자동화`, `신분증 or 명함 정보 인식`, `차량번호판 속 정보 인식`, `파파고 이미지 번역 서비스`, `영수증 속 매장정보와 결제 수단 인식`, `신용카드 카드번호와 유효기간 인식`, `이미지와 비슷한 상품과 최저가 검색하기`

## 📌 기대효과 및 보완점
### ✅ 기대효과
- 네이버 AI OCR 활용     
&rarr; OCR로 기존 업체이름과 사진 속 로고를 비교하여 퍼스널 모빌리티 업체이름을 잘 모르는 사용자도 쉽게 이용 가능
- 간단한 신고 접수  
&rarr; 불편을 느낀 사용자의 참여를 유도할 수 있음
- 정밀한 위치값 측정  
&rarr; 현장 운영팀 출동시 오차범위를 줄여 정확한 위치에 출동하도록 도움
- 신고내역 축적
&rarr; 무단주차 빈번하게 발생하는 지역에 대한 정보 수집과 퍼스널 모빌리티 관련 대책 마련에 사용될 수 있음.
### ✅ 보완점과 향후 계획
- 신고 후 보상 제공하는 기능 추가
- 신고내역 확인하는 기능 추가
- 새로운 업체 추가되면 로고 구분할 수 있도록 코드 관리
- 객체탐지로 자전거 or 킥보드 스스로 구분하는 기능 추가

## 📌 ISSUE
- 퍼스널 모빌리티에 있는 대부분의 회사명에 디자인이 들어가 있어 제대로 읽지 못한다.
- [NetworkOnMainThreadException](https://github.com/lijunhyeong/Study/blob/main/Android/ISSUE/Thread%20%EA%B4%80%EB%A0%A8%20%EC%97%90%EB%9F%AC/NetworkOnMainThreadException.md) ERROR 발생   

## 🛠 개발 환경 및 dependencies
- Android Studio Chipmunk | 2021.2.1 Patch 2
- Kotlin 1.7.10
- kakao Map API
- button toggle group - nl.bryanderidder:themed-toggle-button-group:1.4.1
- Naver AI Services &rarr; CLOVA OCR(인쇄물 속 글자를 추출하여 디지털 데이터로 변환해 주는 서비스)



