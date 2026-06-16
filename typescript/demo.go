package main

import (
	"errors"
	"fmt"
)

func div(a int, b int) (int, error) {
	if b == 0 {
		return 0, errors.New("divide by zero error")
	}
	return a / b, nil
}
func main() {
	data, err := div(10, 0)
	if err != nil {
		fmt.Println("Error ", err)
	} else {
		fmt.Println("Result ", data)
	}
}
