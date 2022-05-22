# QuranAppCoroutine :book: 

<a href="https://www.linkedin.com/in/adel-ayman-2497ab1b3/">
    <img src="https://img.shields.io/badge/LinkedIn-blue?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn Badge"/>
  </a>
  
This repository contains a **quran app** that implements Coroutines , mvvm architecture , clean architecture , navigation component , hilt , etc.... using kotlin language


![](https://github.com/adelayman1/QuranApp./blob/master/images/allAppImage.jpg)

## App Overview

- this screen view all surahs in quran
<img src="https://github.com/adelayman1/QuranApp./blob/master/images/image1.jpg" width="300" />

- this screen view surah the user selected it
<img src="https://github.com/adelayman1/QuranApp./blob/master/images/image2.jpg" width="300" />
<img src="https://github.com/adelayman1/QuranApp./blob/master/images/image3.jpg" width="300" />



## Built With 🛠

*  [Kotlin](https://kotlinlang.org/) 
*  [Coroutines](https://developer.android.com/kotlin/coroutines)
*  [StateFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/) 
*  mvvm architecture
*  Clean architecture
*  [Navigation component](https://developer.android.com/guide/navigation)
*  [Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack) 
*  [Retrofit2](https://square.github.io/retrofit/) 
*  Single activity concept 
*  Repository pattern
*  [ViewBinding](https://developer.android.com/topic/libraries/view-binding) 

## Project Structure


    quranapprxjavaversion      
    ├── data                   
    │   ├── api              
    |   │   └── SurahApiService.kt                           
    |   |   
    │   ├── di                  
    |   │   └── module        
    |   |       ├──NetworkModule.kt         
    |   |       └──SurahApiModule.kt
    |   |
    │   ├── repositories         
    |   |   └──SurahRepositoryImpl.kt
    |   |
    |   |
    |   ├── models             
    |   |   ├──ApiResponse.kt   
    |   |   ├──SurahDetailsModel.kt 
    |   |   ├──SurahModel.kt 
    |   |   └──VerseModel.kt 
    |   |
    |   |
    |   |── utils
    |   |   └──ErrorHandlerImpl.kt 
    |
    |       
    |── domain 
    |   ├──enities
    |   |   ├──ErrorEnity.kt
    |   |   └──Result.kt
    |   |
    |   |──repositories
    |   |  └──SurahRepository.kt
    |   |
    |   |──usecases
    |   |  ├──GetAllSurahUseCase.kt
    |   |  └──GetSurahDetailsUseCase.kt
    |   |
    |   |──utils
    |   |  └──ErrorHandler.kt
    |
    |
    ├── presentation                      
    │   ├── homeScreen
    |   |   ├──HomeFragment.kt
    |   |   ├──HomeViewModel.kt
    |   |   └──SurahAdapter.kt
    |   |
    │   ├── surahDetailsScreen
    |   |   ├──SurahDetailsFragment.kt
    |   |   ├──SurahDetailViewModel.kt
    |   |   └──VerseAdapter.kt
    |
    |
    ├──MainActivity.kt
    └── Application.kt
    
       
## LICENSE
```MIT License

Copyright (c) 2022 adelayman1

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.```
