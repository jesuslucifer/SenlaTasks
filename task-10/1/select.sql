USE 10task1;

#1.Найти номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 долларов.
SELECT model, speed, hd FROM pc WHERE price < 500;

#2.Найти производителей принтеров. Вывести поля: maker.
SELECT DISTINCT maker FROM product WHERE type = 'Printer';

#3.Найти номер модели, объем памяти и размеры экранов ноутбуков, цена которых превышает 1000 долларов.
SELECT model, ram, screen FROM laptop WHERE price > 1000;

#4.Найти все записи таблицы Printer для цветных принтеров.
SELECT * FROM printer WHERE color = 'y';

#5.Найти номер модели, скорость и размер жесткого диска для ПК, имеющих скорость cd 12x или 24x и цену менее 600 долларов.
SELECT model, speed, hd FROM pc WHERE (cd = '12x' or cd = '24x') and price < 600;

#6.Указать производителя и скорость для тех ноутбуков, которые имеют жесткий диск объемом не менее 100 Гбайт.
SELECT maker, speed FROM laptop JOIN product ON laptop.model = product.model WHERE hd > 99;

#7.Найти номера моделей и цены всех продуктов (любого типа), выпущенных производителем B (латинская буква).
SELECT pr.model, price FROM laptop it JOIN product pr ON it.model = pr.model WHERE maker = 'B'
UNION SELECT pr.model, price FROM printer pri JOIN product pr ON pri.model = pr.model WHERE maker = 'B'
UNION SELECT pr.model, price FROM pc JOIN product pr ON pc.model = pr.model WHERE maker = 'B';

#8.Найти производителя, выпускающего ПК, но не ноутбуки.
SELECT DISTINCT maker FROM product WHERE type = 'PC' AND maker NOT IN (SELECT maker FROM product WHERE type = 'Laptop');

#9.Найти производителей ПК с процессором не менее 450 Мгц. Вывести поля: maker.
SELECT DISTINCT maker FROM product JOIN pc ON product.model = pc.model WHERE speed > 449;

#10.Найти принтеры, имеющие самую высокую цену. Вывести поля: model, price.
SELECT model, price FROM printer WHERE price = (SELECT MAX(price) FROM printer);

#11.Найти среднюю скорость ПК.
SELECT AVG(speed) FROM pc;

#12.Найти среднюю скорость ноутбуков, цена которых превышает 1000 долларов.
SELECT AVG(speed) FROM laptop WHERE price > 1000;

#13.Найти среднюю скорость ПК, выпущенных производителем A.
SELECT AVG(speed) FROM pc JOIN product ON pc.model = product.model WHERE maker = 'A';

#14.Для каждого значения скорости процессора найти среднюю стоимость ПК с такой же скоростью. Вывести поля: скорость,
#средняя цена.
SELECT speed, AVG(price) FROM pc GROUP BY speed HAVING COUNT(speed) > 1;

#15.Найти размеры жестких дисков, совпадающих у двух и более PC. Вывести поля: hd.
SELECT hd FROM pc GROUP BY hd HAVING COUNT(hd) > 1;

/*16.Найти пары моделей PC, имеющих одинаковые скорость процессора и RAM. В результате каждая пара указывается только один
раз, т.е. (i,j), но не (j,i), Порядок вывода полей: модель с большим номером, модель с меньшим номером, скорость, RAM.*/
SELECT DISTINCT GREATEST(pc1.code, pc2.code),
                LEAST(pc1.code, pc2.code),
                pc1.speed,
                pc1.ram
FROM pc pc1 JOIN pc pc2 WHERE pc1.speed = pc2.speed AND pc1.ram = pc2.ram AND pc1.model != pc2.model;

#17.Найти модели ноутбуков, скорость которых меньше скорости любого из ПК. Вывести поля: type, model, speed.
SELECT type, laptop.model, speed FROM laptop JOIN product ON laptop.model = product.model WHERE speed < (SELECT MIN(speed) FROM pc);

#18.Найти производителей самых дешевых цветных принтеров. Вывести поля: maker, price.
SELECT maker, price FROM printer JOIN product ON printer.model = product.model WHERE color = 'y' and price = (SELECT MIN(price) FROM printer WHERE color = 'y');

#19.Для каждого производителя найти средний размер экрана выпускаемых им ноутбуков.
#Вывести поля: maker, средний размер экрана.
SELECT maker, AVG(screen) FROM laptop JOIN product ON laptop.model = product.model GROUP BY maker;

#20.Найти производителей, выпускающих по меньшей мере три различных модели ПК. Вывести поля: maker, число моделей.
SELECT maker, COUNT(product.model) FROM product JOIN pc ON product.model = pc.model GROUP BY maker HAVING COUNT(product.model) > 2;

#21.Найти максимальную цену ПК, выпускаемых каждым производителем. Вывести поля: maker, максимальная цена.
SELECT maker, MAX(price) FROM product JOIN pc ON product.model = pc.model GROUP BY maker;

#22.Для каждого значения скорости процессора ПК, превышающего 600 МГц, найти среднюю цену ПК с такой же скоростью.
#Вывести поля: speed, средняя цена.
SELECT speed, AVG(price) FROM pc WHERE speed > 600 GROUP BY speed;

#23.Найти производителей, которые производили бы как ПК, так и ноутбуки со скоростью не менее 750 МГц. Вывести поля:maker
SELECT maker FROM product JOIN pc ON product.model = pc.model WHERE pc.speed > 749 and maker IN (SELECT maker FROM laptop JOIN product ON laptop.model = product.model WHERE speed > 749);

#24.Перечислить номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в базе данных продукции.
SELECT model, price
FROM (SELECT model, price FROM pc
      UNION SELECT model, price FROM laptop
      UNION SELECT model, price FROM printer) AS allModels
WHERE price = (SELECT MAX(price) FROM (
    SELECT price FROM pc
    UNION SELECT price FROM laptop
    UNION SELECT price FROM printer) AS maxPrice);


#25.Найти производителей принтеров, которые производят ПК с наименьшим объемом RAM и с самым быстрым процессором среди
#всех ПК, имеющих наименьший объем RAM. Вывести поля: maker
SELECT DISTINCT maker FROM product WHERE model IN (
SELECT model FROM pc WHERE ram = (SELECT MIN(ram) FROM pc)
AND
speed = (SELECT MAX(speed) FROM pc WHERE ram = (SELECT MIN(ram) FROM pc)))
AND
maker IN (SELECT maker FROM product WHERE type = 'Printer');
