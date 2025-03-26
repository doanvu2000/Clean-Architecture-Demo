# Project demo Clean Architecture
### Load data provinces-districts-wards in VietNam from file vietnam_province.db in assets folder
## Technical use:
 - Kotlin version 2.0.21
 - Room Database
 - Hilt Dagger2
 - Using base from [BaseAndroidKotlin](https://github.com/doanvu2000/AndroidBaseKotlin) project
## Project source tree:
 - core:
   - common: Base class: BaseActivity, BaseAdapterRecyclerView, BaseFragment, BaseViewHolder
   - utils: Extension with activity, fragment, context, View, Utils...
 - data:
   - api: interface and class define with api, local data source
   - model: entity
   - repository: Implementation of Repository in domain layer
 - di: AppModule: provide injection
 - domain:
   - repository: interface define function bussiness logic
   - usecase: class using function in repository to access data
 - presentation: ui and viewmodel
 - assets: assets file such as: db file, images, videos, audios, json, html, text file...
