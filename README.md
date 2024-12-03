목표: 개방-폐쇄 원칙, 단일 책임 원칙, 의존성 역전 원칙을 지키는 방식으로 클래스 설계 및 구현
1. 개방-폐쇄 원칙: Model 클래스의 종류가 늘어났을 때 기존 코드는 수정하지 않고 새로운 클래스만 추가하면 되는가?
2. 단일 책임 원칙: 각 클래스의 책임(역할)을 접속사를 사용하지 않고 설명할 수 있는가?
3. 의존성 역전 원칙: 먼저 공통적인 행위와 관계를 인터페이스나 추상 클래스로 정의하고 구현
4. 테스트 코드 작성: 주요 함수들의 단위 테스트, MVC 컴포넌트 상호작용을 Mock으로 단위 테스트 등 

## 전체 구조 클래스 다이어그램

## 사용한 디자인 패턴과 목적
State Pattern
Factory Method
Singleton
Composite
Command
Observer

<!-
# 지금까지의 과정
## abandoned branch (처음 일주일 정도)
JavaFx를 이용한 기능 구현에만 신경을 쓴 나머지 객체지향적이지도 않고 아키텍처도 불분명하여 다시 시작
  
## main branch (abandoned branch 이후-1차 리뷰 및 질의응답)
### 문제점
  - MVVM 모델을 잘못 이해: 데이터 바인딩 없는 컨트롤러를 ViewModel로 이해 → 패키지를 model/viewmodel/view로 잘못 나눔, 전반적으로 패키지에 맞지 않는 분리, 클래스 이름이 역할을 대표하지 못하거나 관용적인 의미와 배치됨
  - View의 의미 잘못 이해: 뷰 개념을 뷰 객체로 이해하여 뷰 내부에서 클래스를 나누지 않음
  - "의존성"을 잘못 이해: 함께 사용되거나, 객체 참조를 하거나, 객체에서 데이터를 꺼내오는 경우까지 의존성으로 이해

### 코드 리뷰와 질의응답을 통해 알게 된 점
  - 프로그램의 전반적 실행 흐름은 View → State → Command → Model → Controller → View
  - "의존성"의 의미: 객체 필드의 값을 원시타입으로 받는 건 의존성이 아님, 인터페이스나 중간자를 두는 것은 의존성을 통제하려는 시도
  - ProgramStatus 클래스는 객체지향 위배: 프로그램 전체 정보를 저장하는 context를 만들어서 selectedItem을 담는 것은 객체지향 위배
  - Bound box의 활용성: 대부분 도형은 bound box를 기준으로 선택 및 조작 가능하도록 구현됨
  - Handler의 명명과 역할: 잡고 늘이는 역할을 해주는 시각적 요소를 핸들러라고 하며 핸들러가 이벤트를 받도록 구현할 수 있음
  - CommandInvoker 구조: 따로 커맨드를 실행하는 Invoker 클래스를 둘 수 있음
  - SelectionManager의 사용 가능성: 따로 selection을 실행하는 SelectionManager 클래스를 둘 수 있음
  - Hit test 개념: 클릭 시에 무엇이 클릭되었는지 확인하는 과정을 hit test라고 하며 이는 각 모델 객체가 수행할 수 있어야 함
  - Model 객체 관리의 중요성: Model 객체의 list를 관리하는 것은 중요한 작업이며 별도의 클래스로 분리하는 것이 나음
    
## MVC (1차 리뷰-방문 수업 질의응답)
### 1차 리뷰를 바탕으로 바꾼 점
  - MVC 모델로의 변경: State(View) → CommandInvoker → Command → Controller → ElementManager/Selector → Controller → View
  - State 패턴을 View로 이전
  - Command 패턴 사용 시도: Command Invoker로 Controller를 Command에 주입, State가 Command 직접 생성, Resize/Translate/Create/SingleSelect까지 구현
  - 5개의 특성을 DTO로 처리 → 확장성 결여, 일부 불필요한 특성도 전달
### 방문수업에서 질문을 통해 알게된 점
  - MVC 모델에서 View가 Model 클래스에 의존성을 가져도 됨 (연관은 아님)
  - DefaultState를 두면 State의 전이를 더 간결히 표현 가능? → 개인적으로 기본값이 Create나 Select여야 한다고 봄
  - 클래스를 설계할 때 가장 중점이 되는 부분이 저장해야 하는 값을 누가 소유하는지, 이에 따라 역할이 정해짐
  - 상위 클래스 객체로 Observer에 통지할 것이 아니라 Model 객체와 View 객체를 Observer로 구체적인 타입을 알 수 있도록 연결해주면 자식에서 확장된 특성 사용 가능 (downcasting?)

## 특히 어려웠던 부분
   1. GUI 프로그램의 작동 방식: hit-test, 모델 객체와 뷰 객체를 분리하여 관리 등
   2. 옵저버 패턴을 사용할 때 보통 최상위 클래스로 자식 클래스 객체를 넘겨주게 되는데 받는 쪽에서는 구체적인 타입을 알아야 할 때
  
## 느낀점
   - 갈아엎고 다시 시작해야만 한다는 느낌은 좋은 징후가 아님, 이는 결국 처음부터 모듈화가 안 되는 모놀리틱한 설계를 했다는 것을 의미
   - 그렇게 하고 싶더라도 있는 것에서 수정하는 연습을 할 것
-->
