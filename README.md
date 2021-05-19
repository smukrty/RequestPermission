# RequestPermission
請求危險的權限

Android 6.0 Marshmallow「權限索取」
#
1.將所需權限定義在 AndroidManifest 
#
2.判斷權限是否已取得

checkSelfPermission
#
3取得權限

requestLocationPermission
#
4.偵測使用者是否按下「拒絕」

當使用者選擇這選項後，權限視窗將不再出現
就算你呼喚了一百次 requestPermissions 它一樣不會理你

Android 提供了一個 method shouldShowRequestPermissionRationale

會回傳 true / false

true = 請給使用者更多的說明

false = 不需再給使用者說明

什麼時候會 return true 呢？

第 n 次按下拒絕

什麼時候會 return false 呢？

a) 已接受

b) 已勾選再也不要問我 -> 因為勾選了代表使用者就是不想給，也就代表你也不需要再給更多說明

當 onRequestPermissionsResult 收到

a) grantResults[0] != PackageManager.PERMISSION_GRANTED

b) shouldShowRequestPermissionRationale == false

以上兩個條件成立時

便是代表使用者勾選了「不要再提醒我」
#
5.偵測使用者是否勾選「不要再問我」選項

!ActivityCompat.shouldShowRequestPermissionRationale

#
6.前往系統設定頁面

openAppSettingsIntent


 
 
#
危險權限

READ_CALENDAR	允許APP讀取行事曆

WRITE_CALENDAR	允許APP寫入行事曆

CAMERA	允許APP使用相機

READ_CONTACTS	允許APP讀取聯絡資訊

WRITE_CONTACTS	允許APP寫入聯絡資訊

GET_ACCOUNTS	取得手機帳號

ACCESS_FINE_LOCATION	允許APP取得精確位置

ACCESS_COARSE_LOCATION	允許APP取得大致位置

RECORD_AUDIO	允許APP錄音

READ_PHONE_STATE	允許APP讀取電話狀態

CALL_PHONE	允許APP打電話

READ_CALL_LOG	允許APP讀取通話紀錄

WRITE_CALL_LOG	允許APP寫入通話紀錄

BODY_SENSORS	允許APP讀取體感sensor

SEND_SMS	允許APP傳送簡訊

RECEIVE_SMS	允許APP接收簡訊

READ_SMS	允許APP讀取簡訊

READ_EXTERNAL_STORAGE	讀取外部儲存空間（SD卡）

WRITE_EXTERNAL_STORAGE	寫入外部儲存空間（SD卡）
