## Q.  데이터의 종류에 따라 2가지로 스트림을 분류하면 어떻게 분류하는가.

- 바이트 스트림: 이진 데이터(Binary Data)를 처리하는 스트림
- 문자 스트림: 문자 데이터를 처리하는 스트림(내부에서 문자 인코딩 적용)

## Q. 데이터를 처리하는 방법에 따라 2가지로 스트림을 분류하면 어떻게 분류하는가.

- 노드 스트림: 입출력 데이터에 가장 먼저 연결되는 스트림
- 필터 스트림: 단독으로 사용할 수 없고, 반드시 노드 스트림에 연결해서 사용

## Q. 입출력의 성능을 개선하기 위해서 사용하는 스트림을 무엇이라고 하는가.

버퍼 기능이 있는 스트림
- BufferedInputStream, BufferedOutputStream
- BufferedReader, BufferedWriter

## Q. 바이트 입력 스트림을 문자 입력 스트림으로 변환하여 사용하는 스트림은 무엇인가.

브릿지 스트림
- 바이트 입력 스트림을 문자 입력 스트림으로 변환: InputStreamReader
- 바이트 출력 스트림을 문자 출력 스트림으로 변환: OutputStreamWriter

## Q. 키보드로부터 라인단위로 문자열을 입력 받기위한 스트림을 생성하시요.(스트림의 연결)

new BufferedReader(new InputStreamReader(System.in));

## Q. 파일에서 문자데이터를 읽어들이는 스트림을 생성하시요(sungjuck.data파일에서)

new BufferedReader(new FileReader("sungjuck.data"));

## Q. 입력 스트림의 read()메서드는 더 이상 데이터를 읽을 수 없을때 스트림의 끝을 나타내는 어떤 값은 리턴하는가.

-1

## Q. BufferedReader에서 라인단위로 데이터를 읽어들이는 메서드는 무엇인가.

readLine()

## Q. 입출력 데이터에 가장 먼저 연결되는 스트림을 무엇이라고 하는가.

노드 스트림

## Q. 스트림과 스트림이 연결될때 스트림 내부에서 처리해주는 것을 무엇이라고 하는가.

저수준의 입출력