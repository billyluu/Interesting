package billy.lab.interesting

class ComeOn {

    companion object {
        operator fun get(int: Int) {
            int + 100
        }
    }
}