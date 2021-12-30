# bitcoinAutoTrade

평소 비트코인에 관심이 있었던 저는 코딩 유튜브를 보고 비트코인 매매를 24시간 자동화해서 할 수 있다는 것을 알고 호기심이 생겨 유튜브를 보며 직접 만들어 보았습니다.

## test.py

test.py에서 현재 비트코인 가격과 보유 현금 조회 그리고 pyupbit에서 지원하는 코인시세를 확인해보았습니다.
```python
print(upbit.get_balance("KRW-BTC"))     # KRW-BTC 조회
print(upbit.get_balance("KRW"))         # 보유 현금 조회
print(pyupbit.get_tickers("KRW"))
```

<img src="https://user-images.githubusercontent.com/89118596/147717391-7cdfc0a0-60dc-4dfd-aca8-53550b5b9a6c.png">



## backtest.py

7일동안의 OHLCV 값을 불러오고 range 컬럼을 만들고 고가와 저가의 차이에 k값을 0.5로 가정하여 곱합니다.
그리고 range는 전 날이기 때문에 .shift를 이용해서 컬럼을 한 칸씩 밑으로 내려줍니다.

그 다음 NumPy 라이브러리를 활용해서 고가가 target 값 보다 높게 되면 매수가 진행되어 종가/목표가 로 수익률이 나오게 됩니다.
고가가 target 값 보다 낮으면 매수를 진행하지 않기 때문에 수익률은 그대로 1이 됩니다.

```python
# OHLCV(open, high, low, close, volume)로 당일 시가, 고가, 저가, 종가, 거래량에 대한 데이터
df = pyupbit.get_ohlcv("KRW-BTC", count=7)


# 변동폭 * k 계산, (고가-저가)*k값
df['range'] = (df['high'] - df['low']) * 0.5

# target(매수가), range 컬럼을 한칸씩 밑으로 내림(.shift(1))
df['target'] = df['open'] + df['range'].shift(1)

print(df)

# ror(수익율), np.where(조건문, 참일때 값, 거짓일때 값)
df['ror'] = np.where(df['high'] > df['target'],
                     df['close'] / df['target'],
                     1)

# 누적 곱 계산(cumprod) => 누적 수익률
df['hpr'] = df['ror'].cumprod()

# Draw Down 계산 (누적 최대 값과 현재 hpr 차이 / 누적 최대값 * 100)
df['dd'] = (df['hpr'].cummax() - df['hpr']) / df['hpr'].cummax() * 100

# MDD 계산
print("MDD(%): ", df['dd'].max())

# 엑셀로 출력
df.to_excel("ohlcv.xlsx")
```
<img src="https://user-images.githubusercontent.com/89118596/147718836-29441d12-bfbb-44e8-ae1e-80edec1ae5b4.png">


## bestk.py

어제의 고가와 저가의 변동 폭에 k 배만큼 상승이 일어났을 때 매수를 진행하는 변동성 돌파 전략을 함수 ror에 입력합니다.
최근 7일 동안의 변동성을 테스트하고 수수료는 일단 없다고 가정하고 진행하였습니다.
k값이 0.1에서 1까지 0.1간격으로 증가하도록 for문을 썼고 ror함수를 통해서 변동성 돌파 전략 백 테스팅을 하고 누적 수익률을 계산하여 돌려줍니다.


```python
def get_ror(k=0.5):
    df = pyupbit.get_ohlcv("KRW-BTC", count=7)
    df['range'] = (df['high'] - df['low']) * k
    df['target'] = df['open'] + df['range'].shift(1)

    df['ror'] = np.where(df['high'] > df['target'],
                         df['close'] / df['target'],
                         1)

    ror = df['ror'].cumprod()[-2]
    return ror


for k in np.arange(0.1, 1.0, 0.1):
    ror = get_ror(k)
    print("%.1f %f" % (k, ror))
 ```
이렇게 k 값이 높을 때 더 수익률이 높았다는 것을 알 수 있었습니다.
<img src="https://user-images.githubusercontent.com/89118596/147717277-a887f2c6-edbb-4661-a36f-523f8ad8b39a.PNG">


## bitcoinAutoTrade.py

while True부터 무한 루프를 반복하면서 자동 매매 코드를 동작시킵니다.

```python
# 자동매매 시작
while True:
    try:
        now = datetime.datetime.now()
        start_time = get_start_time("KRW-BTC")
        end_time = start_time + datetime.timedelta(days=1)

        # 9:00 < 현재 < #8:59:50
        if start_time < now < end_time - datetime.timedelta(seconds=10):
            target_price = get_target_price("KRW-BTC", 0.5)
            current_price = get_current_price("KRW-BTC")
            if target_price < current_price:
                krw = get_balance("KRW")
                if krw > 5000:
                    upbit.buy_market_order("KRW-BTC", krw*0.9995)
        else:
            btc = get_balance("BTC")
            if btc > 0.00008:
                upbit.sell_market_order("KRW-BTC", btc*0.9995)
        time.sleep(1)
    except Exception as e:
        print(e)
        time.sleep(1)
 ```
 
## 클라우드

이렇게 완성된 코드를 가지고 결과를 지켜보기 위해 24시간 컴퓨터를 켜놓아야 하는데 그럴 수 없는 환경이므로 AWS라는 아마존에서 만든 클라우드를 이용하여 24시간 자동 매매 코드가 돌아가게 하여 성공적으로 비트코인 자동 매매프로그램을 완성시켰습니다.











