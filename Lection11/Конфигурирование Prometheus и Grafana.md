### 1. В pom файл нашего серверного приложения добавляю зависимости

1. Spring Actuator — Модуль Spring Framework, предоставляющий различные
возможности мониторинга и управления приложением.
2. Micrometer — это фактически библиотека для сбора метрик, которую можно считать
как SLF4J, но для метрик. Под капотом Spring Actuator, именно Micrometer
обеспечивает все те красивые графики и числа, которые вы видите в своем
мониторинговом решении.
3. Prometheus — это система мониторинга и алертинга с открытым исходным кодом,
которая используется для сбора и хранения временных рядов данных, таких как
метрики приложения.

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
			<version>3.5.3</version>
		</dependency>
		
		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-core</artifactId>
			<version>1.15.1</version>
		</dependency>

		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-registry-prometheus</artifactId>
			<version>1.15.1</version>
		</dependency>
```



### 2. Установка prometheus в docker
на машине установлен docker desctop
Из консоли перехожу в "подсистему windows для linux
команда wsl

1. Находясь в корне перейти в дом дирректорию
```
root@NB-IMBA-040:/#
```
2. В дом дирректории создаём каталог и перемещаемся в него

```
root@NB-IMBA-040:~# mkdir prometheus
root@NB-IMBA-040:~# cd prometheus/
root@NB-IMBA-040:~/prometheus#
```
3. В нём создаём yml файл
```
vim prometheus.yml
```
4. Откроется редактор и в нем пишем

```
global:
  scrape_interval: 15s

scrape_configs:
  - job_name: 'spring_app'
    metrics_path: '/actuator/prometheus'
    static_configs:
    - target: ['host.docker.internal:8080']
```
выходим из режима редактирования Esc, далее :wq

5. Запускаем контейнер prometheus, находясь в том же каталоге это важно для указания пути 
файла конфигурации $(pwd)/prometheus.yml
имя контейнера будет --name prometheus
проброс портов с хоста в контейнер -d -p 9090:9090
имя образа prom/prometheus

```
root@NB-IMBA-040:~/prometheus# docker run --name prometheus -d -p 9090:9090 -v $(pwd)/prometheus.yml prom/prometheus
```

6. Заходим на localhost:9090 и получим prometheus

### 3. Устанавливаем Grafana

1. Запуск докер контейнера, аналогично Prometheus

```
docker run --name=grafana -d -p 3000:3000 grafana/grafana
```

2. Теперь, открываем веб-интерфейс Grafana. По умолчанию это http://localhost:3000/.
Входите с помощью логина admin и пароля admin (менять его — хорошая идея).

3. Первым делом, давайте добавим Prometheus как источник данных. Заходим в
Settings > Data Sources > Add data source и выбираем Prometheus. В поле HTTP URL
вводим адрес, по которому доступен ваш Prometheus (обычно это
http://localhost:9090), в лекции (http://host.docker.internal:9090). Сохраняем и тестируем. Если все настроено правильно,
Grafana скажет, что все отлично.

```
Successfully queried the Prometheus API.
Next, you can start to visualize data by building a dashboard, or by querying data in the Explore view.
```




