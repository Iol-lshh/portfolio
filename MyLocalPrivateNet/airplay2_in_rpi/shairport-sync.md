# rpi와 shairport-sync
- shairport-sync는 airplay2를 지원한다.
## 목표: 
  - 로컬 망 내, airplay2 네트워크 스트리머를 라즈베리 파이로 만들기
## 배경:
  - 애플 뮤직에서는 ALAC을 통해, 나름 고해상력의 음원을 스트리밍한다.
  - 애플의 휴대기기는 블루투스 코덱을 AAC만 지원하며, AAC는 손실률이 크다.
  - 고해상력의 음원을 이용하는 방법은, airplay2를 받는 네트워크 플레이어를 이용하는 것. (~~아니면 유선으로 연결해 듣거나..~~)
  - 네트워크 플레이어는 어마어마한 고가를 자랑하는 장비이다.
  - shairport-sync 덕분에, 라즈베리 파이를 이용하여, 쉽게 구성 가능하다.
  - rpi위에서 돌아가고 있는 다른 서비스와 독립적인 환경으로 구동하기 위해, 도커 위에 올리기로 한다.
- 참조:
  - [깃헙](https://github.com/mikebrady/shairport-sync)
  - [돜헙](https://hub.docker.com/r/mikebrady/shairport-sync)

## 구현
1. usb 위치 확인
```sh
$ aplay -l
...중략...
card 1: AMP [S3 Pro DAC & AMP], device 0: USB Audio [USB Audio]
  Subdevices: 1/1
  Subdevice #0: subdevice #0
...중략...
```
- card=1, device=0 임을 알 수 있다.

2. 도커
- 도커에 대한 세팅과 기본 옵션에 대한 설명은 생략한다.
```sh
sudo docker run -d --restart unless-stopped --net host --device /dev/snd  \
   mikebrady/shairport-sync -a rpi-shairport -- -d hw:CARD=1,DEV=0 -c PCM
```
- alias를 rpi-shairport로 하였다.
- 구동은 hw:CARD=1,DEV=0 USB를 사용
- 컨트롤은 PCM 방식

3. 후기
- rpi는 하드웨어 버전에 따라, usb에 디스토션이 섞여들어오는 이슈가 있다. 다행히 나는 굉장히 깔끔했다.
- 하드웨어의 Revision을 확인
  - (8ram은 고민할 필요 없다.)
  - [RPi_HardwareHistory](https://elinux.org/RPi_HardwareHistory)
```sh
$ cat /proc/cpuinfo
...중략...
Hardware        : BCM2835
Revision        : d03114
...중략...
```

- 한 가지 불만은, S3 Pro DAC & AMP 꼬다리 DAC는 발열이 높고, 이는 대기 전력 소모가 높다는 것 (amp 때문)!
  - 발열이 적은 순수 DAC로 바꿔보자..
