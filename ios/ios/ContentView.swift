import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()

	var body: some View {
        Group {
            HomeView()
        }
        .background(AppColors.background.edgesIgnoringSafeArea(.all))
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
