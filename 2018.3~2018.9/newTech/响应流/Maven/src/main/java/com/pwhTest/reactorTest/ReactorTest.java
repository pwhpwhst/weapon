package com.pwhTest.reactorTest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import reactor.core.publisher.Flux;
import java.util.Random;
import java.util.Arrays;


import java.util.Optional;
import reactor.core.publisher.Mono;

public class ReactorTest{
	public static void main(String args[]){

// 创建序列的方法 begin

//	Flux.just("Hello", "World").subscribe(System.out::println);
//	Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
//	Flux.empty().subscribe(System.out::println);
//	Flux.range(1, 10).subscribe(System.out::println);
//	Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
//	Flux.intervalMillis(1000).subscribe(System.out::println);


//	Flux.generate(sink -> {
//		sink.next("Hello");
//		sink.complete();
//	}).subscribe(System.out::println);
//
//
//	Flux.create(sink -> {
//		for (int i = 0; i < 10; i++) {
//			sink.next(i);
//		}
//		sink.complete();
//	}).subscribe(System.out::println);

//final Random random = new Random();
//Flux.generate(ArrayList::new, (list, sink) -> {
//    int value = random.nextInt(100);
//    list.add(value);
//    sink.next(value);
//    if (list.size() == 10) {
//        sink.complete();
//    }
//    return list;
//}).subscribe(System.out::println);

//	Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
//	Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
//	Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);

// 创建序列的方法 end


// 操作符的使用 begin

//buffer操作
//    	Flux.intervalMillis(100).bufferMillis(1001).take(10).toStream().forEach(System.out::println);
//    	Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
//    	Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);


//    	window 操作符的作用类似于 buffer，所不同的是 window 操作符是把当前流中的元素收集到另外的 Flux 序列中，因此返回值类型是 Flux<Flux<T>>
//    	Flux.range(1, 100).window(20).subscribe(System.out::println);
//    	Flux.intervalMillis(100).windowMillis(1001).take(10).toStream().forEach(System.out::println);
    	
//zipWith操作
//    	Flux.just("a", "b").zipWith(Flux.just("c", "d")).subscribe(System.out::println);
//    	Flux.just("a", "b").zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
//        .subscribe(System.out::println);

//take 系列操作符用来从当前流中提取元素
//Flux.range(1, 1000).take(10).subscribe(System.out::println);
//Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
//Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
//Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);

//	reduce 和 reduceWith 操作符对流中包含的所有元素进行累积操作
//    	Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
//    	Flux.range(1, 100).reduce((x, y) -> {
//    		System.out.println("x:"+x);
//    		System.out.println("y:"+y);
//    		return x + y;}).subscribe(System.out::println);
//    	Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);


//    	进行合并的流都是每隔 100 毫秒产生一个元素，不过第二个流中的每个元素的产生都比第一个流要延迟 50 毫秒。
//    	Flux.merge(Flux.intervalMillis(0, 100).take(5), Flux.intervalMillis(50, 100).take(5))
//        .toStream()
//        .forEach(System.out::println);
//		在使用 merge 的结果流中，来自两个流的元素是按照时间顺序交织在一起；而使用 mergeSequential 的结果流则是首先产生第一个流中的全部元素，再产生第二个流中的全部元素。
//		Flux.mergeSequential(Flux.intervalMillis(0, 100).take(5), Flux.intervalMillis(50, 100).take(5))
//        .toStream()
//        .forEach(System.out::println);

//		flatMap 和 flatMapSequential 操作符把流中的每个元素转换成一个流，再把所有流中的元素进行合并。flatMapSequential 和 flatMap 之间的区别与 mergeSequential 和 merge 之间的区别是一样的。
//    	Flux.just(5, 10).flatMap(x -> Flux.intervalMillis(x * 10, 100).take(x))
//        .toStream().forEach(System.out::println);

//concatMap 操作符的作用也是把流中的每个元素转换成一个流，再把所有流进行合并。
//	与 flatMap 不同的是，concatMap 会根据原始流中的元素顺序依次把转换之后的流进行合并；
//	与 flatMapSequential 不同的是，concatMap 对转换之后的流的订阅是动态进行的，
//	而 flatMapSequential 在合并之前就已经订阅了所有的流。
//    	Flux.just(5, 10)
//        .concatMap(x -> Flux.intervalMillis(x * 10, 100).take(x))
//        .toStream()
//        .forEach(System.out::println);


//combineLatest 操作符把所有流中的最新产生的元素合并成一个新的元素，
//	作为返回结果流中的元素。只要其中任何一个流中产生了新的元素，
//	合并操作就会被执行一次，结果流中就会产生新的元素。
//    	Flux.combineLatest(
//    	        Arrays::toString,
//    	        Flux.intervalMillis(100).take(5),
//    	        Flux.intervalMillis(50, 100).take(5)
//    	).toStream().forEach(System.out::println);

// 操作符的使用 end

//	通过 subscribe()方法处理正常和错误消息,详细看网上的说明
//    	Flux.just(1, 2)
//        .concatWith(Mono.error(new IllegalStateException()))
//        .subscribe(System.out::println, System.err::println);

//Flux.just(1, 2)
//        .concatWith(Mono.error(new IllegalArgumentException()))
//        .onErrorResumeWith(e -> {
//            if (e instanceof IllegalStateException) {
//                return Mono.just(0);
//            } else if (e instanceof IllegalArgumentException) {
//                return Mono.just(-1);
//            }
//            return Mono.empty();
//        })
//        .subscribe(System.out::println);


//	调度器的使用
//    	Flux.create(sink -> {
//    		sink.next(Thread.currentThread().getName());
//    		sink.complete();
//    		}).publishOn(Schedulers.single())
//    		.map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
//    		.publishOn(Schedulers.elastic())
//    		.map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
//    		.subscribeOn(Schedulers.parallel())
//    		.toStream()
//    		.forEach(System.out::println);


		System.out.println("afsadfg");
	}
}