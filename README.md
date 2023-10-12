## WeatherForecaster
Simple App to show current weather and daily forecast for 3-days after.

#### Feature
- Show current temperature, humidity, and wind speed
- Show 3 days daily forecast with high temperature, low temperature, humidity, wind speed
- Add and Delete favorite city
- Search favorite city (limited to Indonesia's city only)

#### High Level Diagram
<img title="HLD" alt="Weather App HLD" src="/app/image/Weather App-HLD.drawio.png">

- User will be shown the UI using Jetpack Compose. 
- When user interact it will trigger the viewmodel to fetch data from usecase. 
- Usecase will call Repository Interface in domain layer and then call the repository implementation in the data layer.
- Repository Impl will call Data Source Interface and then will call remote or local implementation based on the usage.
- Remote data source will fetch data from API using retrofit in API Interface
- Local data source will load/save data to device storage (DB) using DAO
- All of the component will be injected using Hilt Dependency Injection
- Entity fetch in data layer (Dto and LocalEntity) will be mapped into domain layer model before passed back into domain layer
- In the app layer, data could be mapped again into spec model based on the need
- Data will be transfered between layer using Flow and will be collected in viewmodel and passed to the view using state or livedata

#### Low Level Implementation
##### Home Page
- User Open Page
- Fetch current weather condition and 3 days daily forecast for default city (Jakarta) from API
- Load Favorite Location List from DB
- Fetch Weather Condition for all favorite location from API
- If User Click on favorite location, will fetch current weather condition and 3 days daily forecast for the selected location
- If user click add favorite location, will be redirected to search page
- If user click delete on favorite city, will delete data from DB

##### Search Page
- User query keyword
- User click search
- Will fetch city list based on user query
- If user click on the city, it will saved data to DB as favorite city
- Go back to homepage


#### System Design
- Architecture 	: MVVM with Clean Architecture 3 Layer (App, Domain, Data)
- UI 						: Jetpack Compose
- Navigation		: Navigation Graph
- DI						: Hilt
- Data Storage	: Database with Room
- Data Transfer	: Flow, State, LiveData
- Protocols			: RestAPI with Retrofit


#### Used API Endpoint

Get Current Location Weather and Daily Forecast

    Request
    GET api.openweathermap.org/data/3.0/onecall?lat=$lat&lon=$lon&appid=$appId&units=metric&lang=id&exclude=minutely,hourly
    
Reverse Geocode Get City Name

    Request
    GET api.openweathermap.org/geo/1.0/reverse?lat=$lat&lon=$lon&appid=$appId
    
Get Indonesia City Name by Query

    Request
    GET https://parseapi.back4app.com/classes/Indonesiacities_Indonesia_Cities_Database?excludeKeys=population,elevation,timezone&where=%7B%0A++++%22asciiname%22%3A+%7B%0A++++++++%22%24regex%22%3A+%22Bandar+La%22%0A++++%7D%0A%7D
    
    Response
    {"results":[{"objectId":"TNeB4UDi9Y","geonameid":1624917,"name":"Bandar Lampung","asciiname":"Bandar Lampung","alternatenames":"Bandar Lampung,Bandar-Lampung,Bandarlampung,Kota Bandar Lampung,TKG,Tanjungkarang,Tanjungkarang-Telukbetung,Telukbetung,ban da nan bang,bandaleulampung,bandaruranpun,bndr lampwng,______-_______,____ _______,_________,____,_____","latitude":-5.42917,"longitude":105.26111,"feature_class":"P","feature_code":"PPLA","country_code":"ID","admin1_code":15,"dem":95,"modification_date":"4/10/16","createdAt":"2020-10-08T15:54:45.363Z","updatedAt":"2020-10-08T15:54:45.363Z"}]}

    
#### Screen Shots

<img title="Error View" alt="Homepage Error View" src="/app/image/error page.png" height="500"> <img title="Search View" alt="Search View" src="/app/image/search page.png" height="500">
    
