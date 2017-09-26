# react-native-tmap
React Naitve Tmap Library </br>
For More detail visit [Tmap - SK planet](https://developers.skplanetx.com/apidoc/kor/tmap/) </br>
When you use this module, you have to get **API KEY** </br>
You can get API KEY this site --> <https://developers.skplanetx.com/apidoc/kor/tmap/> </br>

## Important
This module only supported Android

## Install
```bash
npm install --save react-native-tmap
```

## Linking

### Automatically
```bash
react-native link
```

### Manually

#### Android

1) Add 'react-native-tmap' to a project as a dependency in 'package.json' file.
2) Execute 'npm install' to fetch and install a library.
3) Open Android project located in './android' folder.
4) In settings.gradle add following lines :
```groovy
include ':react-native-tmap'
project(':react-native-tmap').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-tmap/android')
```
5) In 'MainApplication.getPackages' import and add RNTmap package:
```java
import com.kwanghyuk.RNTmapPackage;
...
public class MainApplication extends Application implements ReactApplication {
   ...
   @Override
   protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
         new MainReactPackage(),
         new RNTmapPackage()
      );
   }
}
```
6) In 'build.gradle' of 'app' module add following dependency:
```groovy
dependencies {
   ...
   compile project(':react-native-tmap')
   ...
}
```



## Usage
``` 
preparing
```
