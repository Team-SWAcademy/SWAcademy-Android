# 우리의 용기
(2024-1년도 인하대학교 SW 아카데미 2기 탄소중립 프로젝트)

## 목차

1. [**서비스 소개**](#1)
1. [**기술 스택**](#2)
1. [**주요 기능**](#3)
1. [**Android 담당 역할**](#4)
1. [**안드로이드 아키텍처 설계**](#5)

<div id="1"></div>

## 서비스 소개
**♻️ 우리의 용기 (Our Courage)는** 
<br>다회용기 사용을 촉진하고 카페와 협력하여 지속 가능한 소비를 돕는 친환경 플랫폼입니다.

<div id="2"></div>

## 기술 스택
| | Tech Stack  |
| --- | --- |
| 아키텍처 | MVVM , Clean Architecture |
| 의존성 주입 | Hilt |
| 소셜로그인 | KakaoLogin |
| 데이터 연동 | DataBinding |
| 사진 | Coil |
| 서버 연결 | Retrofit2 & OkHttp3, Coroutine   |
| Binding | BindingActivity & BindingFragment |
| RecyclerView | DiffUtil & ListAdapter |
| QRcode | Zxing |

<div id="3"></div>

## 주요 기능
[🔗 서비스 데모 영상 바로가기 Click 👈](https://www.youtube.com/watch?v=GrSHAVIq5mY&feature=youtu.be)
![페이지7 (2)](https://github.com/Team-SWAcademy/SWAcademy-Android/assets/70602631/44d1b651-5680-4822-a57e-89ab5d0bf6ef)
![페이지 8](https://github.com/Team-SWAcademy/SWAcademy-Android/assets/70602631/c0329420-36cb-4d37-8ca8-bd62b1db6eba)
![페이지 9](https://github.com/Team-SWAcademy/SWAcademy-Android/assets/70602631/4f48d6e0-9ebb-4579-a4e3-64a4db46c1a7)

<div id="4"></div>

## Android 담당 역할
|신서현|조은정|
|:-:|:-:|
|<img width="200px" alt="서현" src="https://avatars.githubusercontent.com/u/70602631?s=400&u=5fe72e42ad68663a69e3459bf9acfcae1033b4fc&v=4">|<img src="https://avatars.githubusercontent.com/u/65918936?v=4" width="200px" />|<img width="200" alt="은정" src="https://github.com/EunJung516">
|[@ss99x2002](https://github.com/ss99x2002)|[@EunJung516](https://github.com/EunJung516)|
| `API 연결` `카카오 소셜 로그인` `QR 대여/반납 기능`<br> `홈 화면` `포인트 현황 화면` `다회용기 세부사항 화면` `프로젝트 초기 세팅` | `마이페이지 화면` `스플래시 화면` `로그인 화면` <br> `영수증 인증 화면` `다회용기 세부사항 화면` `카카오 지도` |

<div id="5"></div>

## 안드로이드 아키텍처 설계
### 📁 Foldering
```
📂 swacademy_android
┣ 📂 application
┣ 📂 data
┃ ┣ 📂 datasource
┃ ┣ 📂 interceptor
┃ ┣ 📂 local
┃ ┣ 📂 model
┃ ┣ 📂 repository //impl
┃ ┣ 📂 service
┣ 📂 di
┣ 📂 domain
┃ ┣ 📂 repository // interface
┣ 📂 presentation
┃ ┣ 📂 camera
┃ ┣ 📂 detail
┃ ┣ 📂 home
┃ ┣ 📂 login
┃ ┣ 📂 mypage
┃ ┣ 📂 point
┃ ┣ 📂 rental
┃ ┣ 📂 returns
┃ ┣ 📂 signup
┃ ┣ 📂 tumbler
┣ 📂 util

```
