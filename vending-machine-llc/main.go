package main

import "fmt"

// 定義狀態 interface
type state interface {
	insertCoin()
	selectProduct()
}

// idle 狀態
type idleState struct {
	v *vendingMachine
}

func (i *idleState) insertCoin() {
	fmt.Println("Coin inserted. Switching to insertState")
	i.v.setState(i.v.insertState)
}

func (i *idleState) selectProduct() {
	fmt.Println("Please insert coin first.")
}

// insert 狀態
type insertState struct {
	v *vendingMachine
}

func (i *insertState) insertCoin() {
	fmt.Println("Coin already inserted.")
}

func (i *insertState) selectProduct() {
	fmt.Println("Selected product. Switching to selectState")
	i.v.setState(i.v.selectState)
}

// select 狀態
type selectState struct {
	v *vendingMachine
}

func (s *selectState) insertCoin() {
	fmt.Println("Coin already inserted.")
}

func (s *selectState) selectProduct() {
	fmt.Println("Product dispensed. Returning to idleState.")
	s.v.setState(s.v.idleState)
}

// vendingMachine 結構
type vendingMachine struct {
	idleState    *idleState
	insertState  *insertState
	selectState  *selectState
	currentState state
}

// 建立 vendingMachine 實例
func newVendingMachine() *vendingMachine {
	v := &vendingMachine{}
	v.idleState = &idleState{v}
	v.insertState = &insertState{v}
	v.selectState = &selectState{v}
	v.currentState = v.idleState
	return v
}

func (v *vendingMachine) setState(s state) {
	v.currentState = s
}

func (v *vendingMachine) insertCoin() {
	v.currentState.insertCoin()
}

func (v *vendingMachine) selectProduct() {
	v.currentState.selectProduct()
}

// 測試流程
func main() {
	vm := newVendingMachine()

	vm.selectProduct() // ❌ 插入硬幣前不能選產品
	vm.insertCoin()    // ✅ 進入 insertState
	vm.insertCoin()    // ❌ 重複插入
	vm.selectProduct() // ✅ 進入 selectState
	vm.selectProduct() // ✅ 出貨並回到 idleState
}
